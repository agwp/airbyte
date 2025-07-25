# BigMailer
An Airbyte connector for [BigMailer](https://bigmailer.com) would facilitate seamless data syncing between BigMailer and other platforms. This connector would allow users to pull data from BigMailer, such as *brands*, *contacts*, *lists*, *fields*, *message types*, *segments*, *bulk campaigns*, *transactional campaigns*, *suppression lists*, and *users*, into various data destinations for further analysis, reporting, or automation tasks.

## Configuration

| Input | Type | Description | Default Value |
|-------|------|-------------|---------------|
| `api_key` | `string` | API Key. API key to use. You can create and find it on the API key management page in your BigMailer account. |  |

## Streams
| Stream Name | Primary Key | Pagination | Supports Full Sync | Supports Incremental |
|-------------|-------------|------------|---------------------|----------------------|
| brands | id | DefaultPaginator | ✅ |  ❌  |
| contacts | id | DefaultPaginator | ✅ |  ❌  |
| lists | id | DefaultPaginator | ✅ |  ❌  |
| fields | id | DefaultPaginator | ✅ |  ❌  |
| message-types | id | DefaultPaginator | ✅ |  ❌  |
| segments | id | DefaultPaginator | ✅ |  ❌  |
| bulk_campaigns | id | DefaultPaginator | ✅ |  ❌  |
| transactional_campaigns | id | DefaultPaginator | ✅ |  ❌  |
| suppression_lists |  | DefaultPaginator | ✅ |  ❌  |
| users | id | DefaultPaginator | ✅ |  ❌  |

## Changelog

<details>
  <summary>Expand to review</summary>

| Version          | Date              | Pull Request | Subject        |
|------------------|-------------------|--------------|----------------|
| 0.0.26 | 2025-07-19 | [63475](https://github.com/airbytehq/airbyte/pull/63475) | Update dependencies |
| 0.0.25 | 2025-06-28 | [62146](https://github.com/airbytehq/airbyte/pull/62146) | Update dependencies |
| 0.0.24 | 2025-05-24 | [60718](https://github.com/airbytehq/airbyte/pull/60718) | Update dependencies |
| 0.0.23 | 2025-05-10 | [59905](https://github.com/airbytehq/airbyte/pull/59905) | Update dependencies |
| 0.0.22 | 2025-05-03 | [59305](https://github.com/airbytehq/airbyte/pull/59305) | Update dependencies |
| 0.0.21 | 2025-04-26 | [58692](https://github.com/airbytehq/airbyte/pull/58692) | Update dependencies |
| 0.0.20 | 2025-04-19 | [58231](https://github.com/airbytehq/airbyte/pull/58231) | Update dependencies |
| 0.0.19 | 2025-04-12 | [57631](https://github.com/airbytehq/airbyte/pull/57631) | Update dependencies |
| 0.0.18 | 2025-04-05 | [57156](https://github.com/airbytehq/airbyte/pull/57156) | Update dependencies |
| 0.0.17 | 2025-03-29 | [56559](https://github.com/airbytehq/airbyte/pull/56559) | Update dependencies |
| 0.0.16 | 2025-03-22 | [56106](https://github.com/airbytehq/airbyte/pull/56106) | Update dependencies |
| 0.0.15 | 2025-03-08 | [55418](https://github.com/airbytehq/airbyte/pull/55418) | Update dependencies |
| 0.0.14 | 2025-03-01 | [54865](https://github.com/airbytehq/airbyte/pull/54865) | Update dependencies |
| 0.0.13 | 2025-02-22 | [54268](https://github.com/airbytehq/airbyte/pull/54268) | Update dependencies |
| 0.0.12 | 2025-02-15 | [53926](https://github.com/airbytehq/airbyte/pull/53926) | Update dependencies |
| 0.0.11 | 2025-02-08 | [53386](https://github.com/airbytehq/airbyte/pull/53386) | Update dependencies |
| 0.0.10 | 2025-02-01 | [52898](https://github.com/airbytehq/airbyte/pull/52898) | Update dependencies |
| 0.0.9 | 2025-01-25 | [52181](https://github.com/airbytehq/airbyte/pull/52181) | Update dependencies |
| 0.0.8 | 2025-01-18 | [51768](https://github.com/airbytehq/airbyte/pull/51768) | Update dependencies |
| 0.0.7 | 2025-01-11 | [51256](https://github.com/airbytehq/airbyte/pull/51256) | Update dependencies |
| 0.0.6 | 2024-12-28 | [50455](https://github.com/airbytehq/airbyte/pull/50455) | Update dependencies |
| 0.0.5 | 2024-12-21 | [50200](https://github.com/airbytehq/airbyte/pull/50200) | Update dependencies |
| 0.0.4 | 2024-12-14 | [49592](https://github.com/airbytehq/airbyte/pull/49592) | Update dependencies |
| 0.0.3 | 2024-12-12 | [49287](https://github.com/airbytehq/airbyte/pull/49287) | Update dependencies |
| 0.0.2 | 2024-12-11 | [49031](https://github.com/airbytehq/airbyte/pull/49031) | Starting with this version, the Docker image is now rootless. Please note that this and future versions will not be compatible with Airbyte versions earlier than 0.64 |
| 0.0.1 | 2024-11-08 | | Initial release by [@parthiv11](https://github.com/parthiv11) via Connector Builder |

</details>
