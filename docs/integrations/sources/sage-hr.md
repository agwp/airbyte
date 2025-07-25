# Sage HR
The Sage HR Airbyte Connector enables seamless data integration, allowing you to extract employee and HR data from Sage HR into your preferred data warehouse or analytics tool. Simplify data syncing for effortless employee management and streamline HR workflows.

## Configuration

| Input | Type | Description | Default Value |
|-------|------|-------------|---------------|
| `api_key` | `string` | API Key.  |  |
| `subdomain` | `string` | subdomain.  |  |

## Streams
| Stream Name | Primary Key | Pagination | Supports Full Sync | Supports Incremental |
|-------------|-------------|------------|---------------------|----------------------|
| employees | id | DefaultPaginator | ✅ |  ❌  |
| leave-management-policies | id | No pagination | ✅ |  ❌  |
| documents | id | DefaultPaginator | ✅ |  ❌  |
| positions | id | DefaultPaginator | ✅ |  ❌  |
| teams | id | DefaultPaginator | ✅ |  ❌  |
| terminated-employees | id | DefaultPaginator | ✅ |  ❌  |
| termination-reasons | id | DefaultPaginator | ✅ |  ❌  |
| individual-allowances | id | DefaultPaginator | ✅ |  ❌  |
| document-categories | id | No pagination | ✅ |  ❌  |
| offboarding-categories | id | DefaultPaginator | ✅ |  ❌  |
| onboarding-categories | id | DefaultPaginator | ✅ |  ❌  |
| leave-requests | id | DefaultPaginator | ✅ |  ❌  |

## Changelog

<details>
  <summary>Expand to review</summary>

| Version          | Date              | Pull Request | Subject        |
|------------------|-------------------|--------------|----------------|
| 0.0.32 | 2025-07-26 | [63982](https://github.com/airbytehq/airbyte/pull/63982) | Update dependencies |
| 0.0.31 | 2025-07-12 | [63041](https://github.com/airbytehq/airbyte/pull/63041) | Update dependencies |
| 0.0.30 | 2025-07-05 | [62724](https://github.com/airbytehq/airbyte/pull/62724) | Update dependencies |
| 0.0.29 | 2025-06-28 | [62280](https://github.com/airbytehq/airbyte/pull/62280) | Update dependencies |
| 0.0.28 | 2025-06-21 | [61301](https://github.com/airbytehq/airbyte/pull/61301) | Update dependencies |
| 0.0.27 | 2025-05-25 | [60488](https://github.com/airbytehq/airbyte/pull/60488) | Update dependencies |
| 0.0.26 | 2025-05-10 | [60135](https://github.com/airbytehq/airbyte/pull/60135) | Update dependencies |
| 0.0.25 | 2025-05-04 | [59576](https://github.com/airbytehq/airbyte/pull/59576) | Update dependencies |
| 0.0.24 | 2025-04-27 | [58999](https://github.com/airbytehq/airbyte/pull/58999) | Update dependencies |
| 0.0.23 | 2025-04-19 | [58400](https://github.com/airbytehq/airbyte/pull/58400) | Update dependencies |
| 0.0.22 | 2025-04-12 | [57979](https://github.com/airbytehq/airbyte/pull/57979) | Update dependencies |
| 0.0.21 | 2025-04-05 | [57438](https://github.com/airbytehq/airbyte/pull/57438) | Update dependencies |
| 0.0.20 | 2025-03-29 | [56735](https://github.com/airbytehq/airbyte/pull/56735) | Update dependencies |
| 0.0.19 | 2025-03-22 | [56181](https://github.com/airbytehq/airbyte/pull/56181) | Update dependencies |
| 0.0.18 | 2025-03-08 | [55534](https://github.com/airbytehq/airbyte/pull/55534) | Update dependencies |
| 0.0.17 | 2025-03-01 | [55027](https://github.com/airbytehq/airbyte/pull/55027) | Update dependencies |
| 0.0.16 | 2025-02-23 | [54551](https://github.com/airbytehq/airbyte/pull/54551) | Update dependencies |
| 0.0.15 | 2025-02-15 | [53970](https://github.com/airbytehq/airbyte/pull/53970) | Update dependencies |
| 0.0.14 | 2025-02-08 | [53496](https://github.com/airbytehq/airbyte/pull/53496) | Update dependencies |
| 0.0.13 | 2025-02-01 | [52966](https://github.com/airbytehq/airbyte/pull/52966) | Update dependencies |
| 0.0.12 | 2025-01-25 | [52505](https://github.com/airbytehq/airbyte/pull/52505) | Update dependencies |
| 0.0.11 | 2025-01-18 | [51899](https://github.com/airbytehq/airbyte/pull/51899) | Update dependencies |
| 0.0.10 | 2025-01-11 | [51323](https://github.com/airbytehq/airbyte/pull/51323) | Update dependencies |
| 0.0.9 | 2024-12-28 | [50687](https://github.com/airbytehq/airbyte/pull/50687) | Update dependencies |
| 0.0.8 | 2024-12-21 | [50300](https://github.com/airbytehq/airbyte/pull/50300) | Update dependencies |
| 0.0.7 | 2024-12-14 | [49687](https://github.com/airbytehq/airbyte/pull/49687) | Update dependencies |
| 0.0.6 | 2024-12-12 | [49353](https://github.com/airbytehq/airbyte/pull/49353) | Update dependencies |
| 0.0.5 | 2024-12-11 | [49089](https://github.com/airbytehq/airbyte/pull/49089) | Starting with this version, the Docker image is now rootless. Please note that this and future versions will not be compatible with Airbyte versions earlier than 0.64 |
| 0.0.4 | 2024-11-05 | [48353](https://github.com/airbytehq/airbyte/pull/48353) | Revert to source-declarative-manifest v5.17.0 |
| 0.0.3 | 2024-11-05 | [48328](https://github.com/airbytehq/airbyte/pull/48328) | Update dependencies |
| 0.0.2 | 2024-10-28 | [47581](https://github.com/airbytehq/airbyte/pull/47581) | Update dependencies |
| 0.0.1 | 2024-10-08 | | Initial release by [@parthiv11](https://github.com/parthiv11) via Connector Builder |

</details>
