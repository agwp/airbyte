/*
 * Copyright (c) 2024 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.cdk.read.cdc

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings
import io.airbyte.cdk.ClockFactory
import io.airbyte.cdk.StreamIdentifier
import io.airbyte.cdk.command.OpaqueStateValue
import io.airbyte.cdk.data.IntCodec
import io.airbyte.cdk.data.TextCodec
import io.airbyte.cdk.discover.Field
import io.airbyte.cdk.discover.IntFieldType
import io.airbyte.cdk.discover.TestMetaFieldDecorator
import io.airbyte.cdk.output.BufferingOutputConsumer
import io.airbyte.cdk.output.DataChannelFormat
import io.airbyte.cdk.output.DataChannelMedium
import io.airbyte.cdk.output.sockets.FieldValueEncoder
import io.airbyte.cdk.output.sockets.NativeRecordPayload
import io.airbyte.cdk.output.sockets.toJson
import io.airbyte.cdk.read.ConcurrencyResource
import io.airbyte.cdk.read.ConfiguredSyncMode
import io.airbyte.cdk.read.FieldValueChange
import io.airbyte.cdk.read.Global
import io.airbyte.cdk.read.GlobalFeedBootstrap
import io.airbyte.cdk.read.PartitionReadCheckpoint
import io.airbyte.cdk.read.PartitionReader
import io.airbyte.cdk.read.ResourceAcquirer
import io.airbyte.cdk.read.ResourceType.RESOURCE_DB_CONNECTION
import io.airbyte.cdk.read.Stream
import io.airbyte.cdk.read.StreamRecordConsumer
import io.airbyte.cdk.util.Jsons
import io.airbyte.protocol.models.v0.AirbyteRecordMessage
import io.airbyte.protocol.models.v0.StreamDescriptor
import io.debezium.document.DocumentReader
import io.debezium.document.DocumentWriter
import io.debezium.relational.history.HistoryRecord
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import java.time.Duration
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * This test class verifies that the [CdcPartitionReader] is able to correctly start and stop the
 * Debezium Engine. As there is no useful way to mock the Debezium Engine, the test is actually an
 * integration test and this class is subclassed for multiple Debezium implementations which connect
 * to a corresponding testcontainer data source.
 */
@SuppressFBWarnings(value = ["NP_NONNULL_RETURN_VIOLATION"], justification = "Micronaut DI")
@ExtendWith(MockKExtension::class)
abstract class AbstractCdcPartitionReaderTest<T : Comparable<T>, C : AutoCloseable>(
    namespace: String?,
    val heartbeat: Duration = Duration.ofMillis(100),
    val timeout: Duration = Duration.ofSeconds(10),
) {
    @MockK lateinit var resourceAcquirer: ResourceAcquirer
    @MockK lateinit var feedBootstrap: GlobalFeedBootstrap
    lateinit var outputConsumer: BufferingOutputConsumer
    lateinit var streamRecordConsumers: Map<StreamIdentifier, StreamRecordConsumer>

    val stream =
        Stream(
            id = StreamIdentifier.from(StreamDescriptor().withName("tbl").withNamespace(namespace)),
            schema = setOf(Field("v", IntFieldType), TestMetaFieldDecorator.GlobalCursor),
            configuredSyncMode = ConfiguredSyncMode.INCREMENTAL,
            configuredPrimaryKey = null,
            configuredCursor = TestMetaFieldDecorator.GlobalCursor,
        )

    val global: Global
        get() = Global(listOf(stream))

    abstract fun createContainer(): C
    abstract fun C.createStream()
    abstract fun C.insert12345()
    abstract fun C.update135()
    abstract fun C.delete24()

    abstract fun createCdcPartitionsCreatorDbzOps(): CdcPartitionsCreatorDebeziumOperations<T>
    abstract fun createCdcPartitionReaderDbzOps(): CdcPartitionReaderDebeziumOperations<T>
    val container: C by lazy { createContainer() }
    private val cdcPartitionsCreatorDbzOps by lazy { createCdcPartitionsCreatorDbzOps() }
    private val cdcPartitionReaderDbzOps by lazy { createCdcPartitionReaderDbzOps() }

    @BeforeEach
    fun setup() {
        setOutputConsumers()
        every { feedBootstrap.dataChannelMedium } returns DataChannelMedium.STDIO
        every { feedBootstrap.dataChannelFormat } returns DataChannelFormat.JSONL
        every { feedBootstrap.outputConsumer } returns outputConsumer
        every { feedBootstrap.streamRecordConsumers() } returns streamRecordConsumers
        every { feedBootstrap.feeds } returns listOf(global, stream)
        every { resourceAcquirer.tryAcquire(any()) } returns
            mapOf(RESOURCE_DB_CONNECTION to ConcurrencyResource.AcquiredThread {})
    }

    private fun setOutputConsumers() {
        outputConsumer = BufferingOutputConsumer(ClockFactory().fixed())
        streamRecordConsumers =
            mapOf(
                stream.id to
                    object : StreamRecordConsumer {
                        override val stream: Stream = this@AbstractCdcPartitionReaderTest.stream
                        override fun accept(
                            recordData: NativeRecordPayload,
                            changes: Map<Field, FieldValueChange>?
                        ) {
                            outputConsumer.accept(
                                AirbyteRecordMessage()
                                    .withStream(stream.name)
                                    .withNamespace(stream.namespace)
                                    .withData(recordData.toJson())
                            )
                        }

                        override fun close() {}
                    }
            )
    }

    @Test
    /**
     * The [integrationTest] method sets up (and tears down) a testcontainer for the data source
     * using [createContainer] and provisions it using [createStream], [insert12345], [update135]
     * and [delete24].
     *
     * While doing so, it creates several [CdcPartitionReader] instances using [currentPosition],
     * [syntheticInput] and [debeziumProperties], and exercises all [PartitionReader] methods.
     */
    fun integrationTest() {
        container.createStream()
        val i0 =
            ReadInput(
                cdcPartitionsCreatorDbzOps.generateColdStartProperties(listOf(stream)),
                cdcPartitionsCreatorDbzOps.generateColdStartOffset(),
                schemaHistory = null,
                isSynthetic = true,
            )
        val p0: T = cdcPartitionsCreatorDbzOps.position(i0.offset)
        val r0: ReadResult = read(i0, p0)
        Assertions.assertEquals(emptyList<Record>(), r0.records)
        Assertions.assertNotEquals(
            CdcPartitionReader.CloseReason.RECORD_REACHED_TARGET_POSITION,
            r0.closeReason,
        )

        container.insert12345()
        val insert =
            listOf<Record>(
                Insert(1, 1),
                Insert(2, 2),
                Insert(3, 3),
                Insert(4, 4),
                Insert(5, 5),
            )
        container.update135()
        val update =
            listOf<Record>(
                Update(1, 6),
                Update(3, 7),
                Update(5, 8),
            )
        val p1: T =
            cdcPartitionsCreatorDbzOps.position(
                cdcPartitionsCreatorDbzOps.generateColdStartOffset()
            )
        container.delete24()
        val delete =
            listOf<Record>(
                Delete(2),
                Delete(4),
            )
        val p2: T =
            cdcPartitionsCreatorDbzOps.position(
                cdcPartitionsCreatorDbzOps.generateColdStartOffset()
            )

        Assertions.assertTrue(r0.state is ValidDebeziumWarmStartState)
        val i1 =
            ReadInput(
                cdcPartitionsCreatorDbzOps.generateWarmStartProperties(listOf(stream)),
                (r0.state as ValidDebeziumWarmStartState).offset,
                r0.state.schemaHistory,
                isSynthetic = false,
            )
        val r1: ReadResult = read(i1, p1)
        Assertions.assertEquals(insert + update, r1.records.take(insert.size + update.size))
        Assertions.assertNotNull(r1.closeReason)

        val r2: ReadResult = read(i1, p2)
        Assertions.assertEquals(
            insert + update + delete,
            r2.records.take(insert.size + update.size + delete.size),
        )
        Assertions.assertNotNull(r2.closeReason)
        Assertions.assertNotEquals(
            CdcPartitionReader.CloseReason.RECORD_REACHED_TARGET_POSITION,
            r2.closeReason
        )
    }

    private fun read(
        input: ReadInput,
        upperBound: T,
    ): ReadResult {
        setOutputConsumers()
        val reader =
            CdcPartitionReader(
                resourceAcquirer,
                cdcPartitionReaderDbzOps,
                upperBound,
                input.properties,
                input.offset,
                input.schemaHistory,
                input.isSynthetic,
                feedBootstrap
            )
        Assertions.assertEquals(
            PartitionReader.TryAcquireResourcesStatus.READY_TO_RUN,
            reader.tryAcquireResources(),
        )
        val checkpoint: PartitionReadCheckpoint
        try {
            runBlocking {
                try {
                    withTimeout(timeout.toMillis()) { reader.run() }
                } catch (_: TimeoutCancellationException) {}
            }
            checkpoint = reader.checkpoint()
        } finally {
            reader.releaseResources()
        }
        // Sanity checks. If any of these fail, particularly after a debezium version change,
        // it's important to understand why.
        Assertions.assertEquals(checkpoint.numRecords.toInt(), outputConsumer.records().size)
        Assertions.assertEquals(checkpoint.numRecords, reader.numEmittedRecords.get())
        Assertions.assertEquals(
            reader.numEvents.get(),
            reader.numEmittedRecords.get() +
                reader.numDiscardedRecords.get() +
                reader.numHeartbeats.get() +
                reader.numTombstones.get()
        )
        Assertions.assertEquals(0, reader.numDiscardedRecords.get())
        Assertions.assertEquals(0, reader.numEventsWithoutSourceRecord.get())
        Assertions.assertEquals(0, reader.numSourceRecordsWithoutPosition.get())
        Assertions.assertEquals(0, reader.numEventValuesWithoutPosition.get())
        return ReadResult(
            outputConsumer.records().map { Jsons.treeToValue(it.data, Record::class.java) },
            cdcPartitionsCreatorDbzOps.deserializeState(checkpoint.opaqueStateValue),
            reader.closeReasonReference.get(),
        )
    }

    data class ReadInput(
        val properties: Map<String, String>,
        val offset: DebeziumOffset,
        val schemaHistory: DebeziumSchemaHistory?,
        val isSynthetic: Boolean,
    )

    data class ReadResult(
        val records: List<Record>,
        val state: DebeziumWarmStartState,
        val closeReason: CdcPartitionReader.CloseReason?,
    )

    @JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
    @JsonSubTypes(
        JsonSubTypes.Type(value = Insert::class),
        JsonSubTypes.Type(value = Update::class),
        JsonSubTypes.Type(value = Delete::class),
    )
    sealed interface Record {
        val id: Int
    }
    data class Insert(override val id: Int, val v: Int) : Record
    data class Update(override val id: Int, val v: Int) : Record
    data class Delete(override val id: Int, val ignore: Boolean = false) : Record

    abstract inner class AbstractCdcPartitionsCreatorDbzOps<T : Comparable<T>> :
        CdcPartitionsCreatorDebeziumOperations<T> {

        override fun deserializeState(opaqueStateValue: OpaqueStateValue): DebeziumWarmStartState {
            val offsetNode: ObjectNode = opaqueStateValue["offset"] as ObjectNode
            val offset =
                DebeziumOffset(
                    offsetNode
                        .fields()
                        .asSequence()
                        .map { Jsons.readTree(it.key) to Jsons.readTree(it.value.asText()) }
                        .toMap(),
                )
            val historyNode: ArrayNode? = opaqueStateValue["schemaHistory"] as? ArrayNode
            val schemaHistory: DebeziumSchemaHistory? =
                if (historyNode != null) {
                    DebeziumSchemaHistory(
                        historyNode.elements().asSequence().toList().map {
                            HistoryRecord(DocumentReader.defaultReader().read(it.asText()))
                        },
                    )
                } else {
                    null
                }
            return ValidDebeziumWarmStartState(offset, schemaHistory)
        }
    }

    abstract inner class AbstractCdcPartitionReaderDbzOps<T : Comparable<T>> :
        CdcPartitionReaderDebeziumOperations<T> {

        override fun deserializeRecord(
            key: DebeziumRecordKey,
            value: DebeziumRecordValue,
            stream: Stream
        ): DeserializedRecord {
            val id: Int = key.element("id").asInt()
            val after: Int? = value.after["v"]?.asInt()
            val record: Record =
                if (after == null) {
                    Delete(id)
                } else if (value.before["v"] == null) {
                    Insert(id, after)
                } else {
                    Update(id, after)
                }

            return DeserializedRecord(
                data =
                    mutableMapOf<String, FieldValueEncoder<*>>(
                            "id" to FieldValueEncoder(record.id, IntCodec),
                            "@c" to FieldValueEncoder(record::class.java.name, TextCodec)
                        )
                        .also {
                            when (record) {
                                is Insert -> it["v"] = FieldValueEncoder(record.v, IntCodec)
                                is Update -> it["v"] = FieldValueEncoder(record.v, IntCodec)
                                is Delete -> {}
                            }
                        },
                changes = emptyMap(),
            )
        }

        override fun findStreamNamespace(
            key: DebeziumRecordKey,
            value: DebeziumRecordValue
        ): String? = stream.id.namespace

        override fun findStreamName(key: DebeziumRecordKey, value: DebeziumRecordValue): String? =
            stream.id.name

        override fun serializeState(
            offset: DebeziumOffset,
            schemaHistory: DebeziumSchemaHistory?
        ): OpaqueStateValue =
            Jsons.valueToTree(
                mapOf(
                    "offset" to
                        offset.wrapped
                            .map {
                                Jsons.writeValueAsString(it.key) to
                                    Jsons.writeValueAsString(it.value)
                            }
                            .toMap(),
                    "schemaHistory" to
                        schemaHistory?.wrapped?.map {
                            DocumentWriter.defaultWriter().write(it.document())
                        },
                ),
            )
    }
}
