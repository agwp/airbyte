# Statsig
Statsig is the single platform to ship, test and analyze new features. Statsig provides the most advanced Experimentation and Feature Flagging tools available, in a platform with full-featured Product Analytics, Session Replay, and more.

## Configuration

| Input | Type | Description | Default Value |
|-------|------|-------------|---------------|
| `api_key` | `string` | API Key.  |  |
| `start_date` | `string` | Start date.  |  |
| `end_date` | `string` | End date.  |  |

See the [API docs](https://docs.statsig.com/http-api) for steps to generate the API key.

## Streams
| Stream Name | Primary Key | Pagination | Supports Full Sync | Supports Incremental |
|-------------|-------------|------------|---------------------|----------------------|
| audit_logs | id | DefaultPaginator | ✅ |  ❌  |
| autotunes | id | DefaultPaginator | ✅ |  ✅  |
| dynamic_configs | id | DefaultPaginator | ✅ |  ✅  |
| dynamic_configs_versions | id.version | DefaultPaginator | ✅ |  ✅  |
| dynamic_configs_rules | id | DefaultPaginator | ✅ |  ❌  |
| events |  | DefaultPaginator | ✅ |  ❌  |
| events_metrics | id | DefaultPaginator | ✅ |  ✅  |
| experiments | id | DefaultPaginator | ✅ |  ✅  |
| gates | id | DefaultPaginator | ✅ |  ✅  |
| gates_rules | id | DefaultPaginator | ✅ |  ❌  |
| holdouts | id | DefaultPaginator | ✅ |  ✅  |
| ingestion_status |  | No pagination | ✅ |  ✅  |
| ingestion_runs | runID | DefaultPaginator | ✅ |  ✅  |
| layers | id | DefaultPaginator | ✅ |  ✅  |
| metrics | id | DefaultPaginator | ✅ |  ❌  |
| metrics_values |  | DefaultPaginator | ✅ |  ❌  |
| segments | id | DefaultPaginator | ✅ |  ✅  |
| segments_ids |  | DefaultPaginator | ✅ |  ❌  |
| tags | id | DefaultPaginator | ✅ |  ❌  |
| target_apps | id | DefaultPaginator | ✅ |  ❌  |
| users |  | DefaultPaginator | ✅ |  ❌  |

## Changelog

<details>
  <summary>Expand to review</summary>

| Version | Date | Pull Request | Subject |
|---------|------|--------------|---------|
| 0.0.27 | 2025-07-26 | [64003](https://github.com/airbytehq/airbyte/pull/64003) | Update dependencies |
| 0.0.26 | 2025-07-12 | [63062](https://github.com/airbytehq/airbyte/pull/63062) | Update dependencies |
| 0.0.25 | 2025-06-28 | [62291](https://github.com/airbytehq/airbyte/pull/62291) | Update dependencies |
| 0.0.24 | 2025-06-14 | [61617](https://github.com/airbytehq/airbyte/pull/61617) | Update dependencies |
| 0.0.23 | 2025-05-24 | [60201](https://github.com/airbytehq/airbyte/pull/60201) | Update dependencies |
| 0.0.22 | 2025-05-04 | [59633](https://github.com/airbytehq/airbyte/pull/59633) | Update dependencies |
| 0.0.21 | 2025-04-27 | [59035](https://github.com/airbytehq/airbyte/pull/59035) | Update dependencies |
| 0.0.20 | 2025-04-19 | [58381](https://github.com/airbytehq/airbyte/pull/58381) | Update dependencies |
| 0.0.19 | 2025-04-12 | [57947](https://github.com/airbytehq/airbyte/pull/57947) | Update dependencies |
| 0.0.18 | 2025-04-05 | [57408](https://github.com/airbytehq/airbyte/pull/57408) | Update dependencies |
| 0.0.17 | 2025-03-29 | [56878](https://github.com/airbytehq/airbyte/pull/56878) | Update dependencies |
| 0.0.16 | 2025-03-22 | [56319](https://github.com/airbytehq/airbyte/pull/56319) | Update dependencies |
| 0.0.15 | 2025-03-08 | [55596](https://github.com/airbytehq/airbyte/pull/55596) | Update dependencies |
| 0.0.14 | 2025-03-01 | [55104](https://github.com/airbytehq/airbyte/pull/55104) | Update dependencies |
| 0.0.13 | 2025-02-22 | [54492](https://github.com/airbytehq/airbyte/pull/54492) | Update dependencies |
| 0.0.12 | 2025-02-15 | [54101](https://github.com/airbytehq/airbyte/pull/54101) | Update dependencies |
| 0.0.11 | 2025-02-08 | [53586](https://github.com/airbytehq/airbyte/pull/53586) | Update dependencies |
| 0.0.10 | 2025-02-01 | [53076](https://github.com/airbytehq/airbyte/pull/53076) | Update dependencies |
| 0.0.9 | 2025-01-25 | [52458](https://github.com/airbytehq/airbyte/pull/52458) | Update dependencies |
| 0.0.8 | 2025-01-18 | [51986](https://github.com/airbytehq/airbyte/pull/51986) | Update dependencies |
| 0.0.7 | 2025-01-11 | [51440](https://github.com/airbytehq/airbyte/pull/51440) | Update dependencies |
| 0.0.6 | 2024-12-28 | [50755](https://github.com/airbytehq/airbyte/pull/50755) | Update dependencies |
| 0.0.5 | 2024-12-21 | [50310](https://github.com/airbytehq/airbyte/pull/50310) | Update dependencies |
| 0.0.4 | 2024-12-14 | [49776](https://github.com/airbytehq/airbyte/pull/49776) | Update dependencies |
| 0.0.3 | 2024-12-12 | [49419](https://github.com/airbytehq/airbyte/pull/49419) | Update dependencies |
| 0.0.2 | 2024-10-28 | [47473](https://github.com/airbytehq/airbyte/pull/47473) | Update dependencies |
| 0.0.1 | 2024-09-27 | | Initial release by [@topefolorunso](https://github.com/topefolorunso) via Connector Builder |

</details>
