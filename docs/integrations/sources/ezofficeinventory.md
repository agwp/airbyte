# EZOfficeInventory
A manifest only source for EZOfficeInventory. https://ezo.io/ezofficeinventory/

## Configuration

| Input | Type | Description | Default Value |
|-------|------|-------------|---------------|
| `api_key` | `string` | API Key. Your EZOfficeInventory Access Token. API Access is disabled by default. Enable API Access in Settings &gt; Integrations &gt; API Integration and click on Update to generate a new access token |  |
| `subdomain` | `string` | Subdomain. The company name used in signup, also visible in the URL when logged in. |  |
| `start_date` | `string` | Start date. Earliest date you want to sync historical streams (inventory_histories, asset_histories, asset_stock_histories) from |  |

## Streams
| Stream Name | Primary Key | Pagination | Supports Full Sync | Supports Incremental |
|-------------|-------------|------------|---------------------|----------------------|
| inventories | identifier | DefaultPaginator | ✅ |  ❌  |
| assets | identifier | DefaultPaginator | ✅ |  ❌  |
| checked_out_assets | identifier | DefaultPaginator | ✅ |  ❌  |
| asset_stocks | identifier | DefaultPaginator | ✅ |  ❌  |
| members | id | DefaultPaginator | ✅ |  ❌  |
| locations | id | DefaultPaginator | ✅ |  ❌  |
| groups | id | DefaultPaginator | ✅ |  ❌  |
| subgroups | id | DefaultPaginator | ✅ |  ❌  |
| vendors | id | DefaultPaginator | ✅ |  ❌  |
| labels | id | DefaultPaginator | ✅ |  ❌  |
| custom_fields | id | DefaultPaginator | ✅ |  ❌  |
| purchase_orders | id | DefaultPaginator | ✅ |  ❌  |
| bundles |  | DefaultPaginator | ✅ |  ❌  |
| carts |  | DefaultPaginator | ✅ |  ❌  |
| inventory_histories |  | DefaultPaginator | ✅ |  ✅  |
| asset_histories |  | DefaultPaginator | ✅ |  ✅  |
| asset_stock_histories |  | DefaultPaginator | ✅ |  ✅  |

## Changelog

<details>
  <summary>Expand to review</summary>

| Version | Date       | Pull Request                                             | Subject                                                                                   |
|---------|------------|----------------------------------------------------------|-------------------------------------------------------------------------------------------|
| 0.0.30 | 2025-07-19 | [63544](https://github.com/airbytehq/airbyte/pull/63544) | Update dependencies |
| 0.0.29 | 2025-07-12 | [63020](https://github.com/airbytehq/airbyte/pull/63020) | Update dependencies |
| 0.0.28 | 2025-07-05 | [62763](https://github.com/airbytehq/airbyte/pull/62763) | Update dependencies |
| 0.0.27 | 2025-06-28 | [62355](https://github.com/airbytehq/airbyte/pull/62355) | Update dependencies |
| 0.0.26 | 2025-06-21 | [61943](https://github.com/airbytehq/airbyte/pull/61943) | Update dependencies |
| 0.0.25 | 2025-06-14 | [61235](https://github.com/airbytehq/airbyte/pull/61235) | Update dependencies |
| 0.0.24 | 2025-05-24 | [60380](https://github.com/airbytehq/airbyte/pull/60380) | Update dependencies |
| 0.0.23 | 2025-05-10 | [59943](https://github.com/airbytehq/airbyte/pull/59943) | Update dependencies |
| 0.0.22 | 2025-05-03 | [58867](https://github.com/airbytehq/airbyte/pull/58867) | Update dependencies |
| 0.0.21 | 2025-04-19 | [58368](https://github.com/airbytehq/airbyte/pull/58368) | Update dependencies |
| 0.0.20 | 2025-04-12 | [57789](https://github.com/airbytehq/airbyte/pull/57789) | Update dependencies |
| 0.0.19 | 2025-04-05 | [57228](https://github.com/airbytehq/airbyte/pull/57228) | Update dependencies |
| 0.0.18 | 2025-03-29 | [56512](https://github.com/airbytehq/airbyte/pull/56512) | Update dependencies |
| 0.0.17 | 2025-03-22 | [55341](https://github.com/airbytehq/airbyte/pull/55341) | Update dependencies |
| 0.0.16 | 2025-03-01 | [54958](https://github.com/airbytehq/airbyte/pull/54958) | Update dependencies |
| 0.0.15 | 2025-02-22 | [54399](https://github.com/airbytehq/airbyte/pull/54399) | Update dependencies |
| 0.0.14 | 2025-02-15 | [53709](https://github.com/airbytehq/airbyte/pull/53709) | Update dependencies |
| 0.0.13 | 2025-02-08 | [53316](https://github.com/airbytehq/airbyte/pull/53316) | Update dependencies |
| 0.0.12 | 2025-02-01 | [52805](https://github.com/airbytehq/airbyte/pull/52805) | Update dependencies |
| 0.0.11 | 2025-01-25 | [51698](https://github.com/airbytehq/airbyte/pull/51698) | Update dependencies |
| 0.0.10 | 2025-01-11 | [51095](https://github.com/airbytehq/airbyte/pull/51095) | Update dependencies |
| 0.0.9 | 2024-12-28 | [50580](https://github.com/airbytehq/airbyte/pull/50580) | Update dependencies |
| 0.0.8 | 2024-12-21 | [50054](https://github.com/airbytehq/airbyte/pull/50054) | Update dependencies |
| 0.0.7 | 2024-12-14 | [49506](https://github.com/airbytehq/airbyte/pull/49506) | Update dependencies |
| 0.0.6 | 2024-12-12 | [49164](https://github.com/airbytehq/airbyte/pull/49164) | Update dependencies |
| 0.0.5 | 2024-12-11 | [48932](https://github.com/airbytehq/airbyte/pull/48932) | Starting with this version, the Docker image is now rootless. Please note that this and future versions will not be compatible with Airbyte versions earlier than 0.64 |
| 0.0.4 | 2024-11-04 | [48180](https://github.com/airbytehq/airbyte/pull/48180) | Update dependencies |
| 0.0.3 | 2024-10-29 | [47913](https://github.com/airbytehq/airbyte/pull/47913) | Update dependencies |
| 0.0.2 | 2024-10-28 | [47535](https://github.com/airbytehq/airbyte/pull/47535) | Update dependencies |
| 0.0.1 | 2024-09-15 | [45590](https://github.com/airbytehq/airbyte/pull/45590) | Initial release by [@pabloescoder](https://github.com/pabloescoder) via Connector Builder |

</details>
