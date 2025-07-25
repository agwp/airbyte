# Freshservice

## Overview

The Freshservice connector supports both full refresh and incremental syncs. You can choose if this connector will copy only the new or updated data, or all rows in the tables and columns you set up for replication, every time a sync is run.

### Output schema

Several output streams are available from this source:

- [Tickets](https://api.freshservice.com/v2/#view_all_ticket) (Incremental)
- [Problems](https://api.freshservice.com/v2/#problems) (Incremental)
- [Changes](https://api.freshservice.com/v2/#changes) (Incremental)
- [Releases](https://api.freshservice.com/v2/#releases) (Incremental)
- [Requesters](https://api.freshservice.com/v2/#requesters)
- [Agents](https://api.freshservice.com/v2/#agents)
- [Locations](https://api.freshservice.com/v2/#locations)
- [Products](https://api.freshservice.com/v2/#products)
- [Vendors](https://api.freshservice.com/v2/#vendors)
- [Assets](https://api.freshservice.com/v2/#assets)
- [PurchaseOrders](https://api.freshservice.com/v2/#purchase-order)
- [Software](https://api.freshservice.com/v2/#software)
- [Satisfaction Survey Responses](https://api.freshservice.com/#ticket_csat_attributes)
- [Requested Items](https://api.freshservice.com/v2/#requested-items)

If there are more endpoints you'd like Airbyte to support, please [create an issue.](https://github.com/airbytehq/airbyte/issues/new/choose)

### Features

| Feature           | Supported? |
| :---------------- | :--------- |
| Full Refresh Sync | Yes        |
| Incremental Sync  | Yes        |
| SSL connection    | Yes        |
| Namespaces        | No         |

### Performance considerations

The Freshservice connector should not run into Freshservice API limitations under normal usage. The API implements rate limiting that varies based on the Freshservice plan:

- Starter: 100 requests/min overall limit
- Growth: 200 requests/min overall limit
- Pro: 400 requests/min overall limit
- Enterprise: 500 requests/min overall limit

There are also sublimits for specific operations (e.g., "View Ticket" which is 50 requests/min for the Starter plan). The connector automatically handles rate limiting with backoff strategies. Please [create an issue](https://github.com/airbytehq/airbyte/issues) if you see any rate limit issues that are not automatically retried successfully.

## Getting started

### Requirements

- Freshservice Account
- Freshservice API Key
- Freshservice domain name (e.g., `your-domain.freshservice.com`)
- Replication Start Date (in ISO 8601 format: `YYYY-MM-DDTHH:MM:SSZ`)

### Setup guide

#### Freshservice API Key

1. Log in to your Freshservice account
2. Navigate to your profile settings by clicking on your profile picture in the top right corner
3. Under the API section, you will find your API key
4. If you don't have an API key, you can generate a new one

For more details, please read [How to find your API key](https://api.freshservice.com/#authentication).

#### Domain Name

Your Freshservice domain is the subdomain of your Freshservice account URL. For example, if you access Freshservice at `https://your-company.freshservice.com`, then your domain name is `your-company.freshservice.com`.

## Reference

This connector uses the [Freshservice REST API v2](https://api.freshservice.com/). All API requests must use HTTPS.

## Changelog

<details>
  <summary>Expand to review</summary>

| Version | Date       | Pull Request                                             | Subject                                                                                |
| :------ | :--------- | :------------------------------------------------------- |:---------------------------------------------------------------------------------------|
| 1.4.32 | 2025-07-26 | [64025](https://github.com/airbytehq/airbyte/pull/64025) | Update dependencies |
| 1.4.31 | 2025-07-19 | [63575](https://github.com/airbytehq/airbyte/pull/63575) | Update dependencies |
| 1.4.30 | 2025-07-12 | [62965](https://github.com/airbytehq/airbyte/pull/62965) | Update dependencies |
| 1.4.29 | 2025-07-05 | [62766](https://github.com/airbytehq/airbyte/pull/62766) | Update dependencies |
| 1.4.28 | 2025-06-28 | [62320](https://github.com/airbytehq/airbyte/pull/62320) | Update dependencies |
| 1.4.27 | 2025-06-21 | [61954](https://github.com/airbytehq/airbyte/pull/61954) | Update dependencies |
| 1.4.26 | 2025-06-14 | [60426](https://github.com/airbytehq/airbyte/pull/60426) | Update dependencies |
| 1.4.25 | 2025-05-10 | [59922](https://github.com/airbytehq/airbyte/pull/59922) | Update dependencies |
| 1.4.24 | 2025-05-03 | [59405](https://github.com/airbytehq/airbyte/pull/59405) | Update dependencies |
| 1.4.23 | 2025-04-26 | [58877](https://github.com/airbytehq/airbyte/pull/58877) | Update dependencies |
| 1.4.22 | 2025-04-19 | [58343](https://github.com/airbytehq/airbyte/pull/58343) | Update dependencies |
| 1.4.21 | 2025-04-12 | [57781](https://github.com/airbytehq/airbyte/pull/57781) | Update dependencies |
| 1.4.20 | 2025-04-05 | [57197](https://github.com/airbytehq/airbyte/pull/57197) | Update dependencies |
| 1.4.19 | 2025-03-29 | [56501](https://github.com/airbytehq/airbyte/pull/56501) | Update dependencies |
| 1.4.18 | 2025-03-22 | [55953](https://github.com/airbytehq/airbyte/pull/55953) | Update dependencies |
| 1.4.17 | 2025-03-08 | [55272](https://github.com/airbytehq/airbyte/pull/55272) | Update dependencies |
| 1.4.16 | 2025-03-01 | [54928](https://github.com/airbytehq/airbyte/pull/54928) | Update dependencies |
| 1.4.15 | 2025-02-22 | [54438](https://github.com/airbytehq/airbyte/pull/54438) | Update dependencies |
| 1.4.14 | 2025-02-15 | [53758](https://github.com/airbytehq/airbyte/pull/53758) | Update dependencies |
| 1.4.13 | 2025-02-08 | [53333](https://github.com/airbytehq/airbyte/pull/53333) | Update dependencies |
| 1.4.12 | 2025-02-01 | [52816](https://github.com/airbytehq/airbyte/pull/52816) | Update dependencies |
| 1.4.11 | 2025-01-25 | [52313](https://github.com/airbytehq/airbyte/pull/52313) | Update dependencies |
| 1.4.10 | 2025-01-18 | [51684](https://github.com/airbytehq/airbyte/pull/51684) | Update dependencies |
| 1.4.9 | 2025-01-11 | [51075](https://github.com/airbytehq/airbyte/pull/51075) | Update dependencies |
| 1.4.8 | 2024-12-28 | [50574](https://github.com/airbytehq/airbyte/pull/50574) | Update dependencies |
| 1.4.7 | 2024-12-21 | [50026](https://github.com/airbytehq/airbyte/pull/50026) | Update dependencies |
| 1.4.6 | 2024-12-14 | [49519](https://github.com/airbytehq/airbyte/pull/49519) | Update dependencies |
| 1.4.5 | 2024-12-12 | [49187](https://github.com/airbytehq/airbyte/pull/49187) | Update dependencies |
| 1.4.4 | 2024-12-11 | [48939](https://github.com/airbytehq/airbyte/pull/48939) | Starting with this version, the Docker image is now rootless. Please note that this and future versions will not be compatible with Airbyte versions earlier than 0.64 |
| 1.4.3 | 2024-10-29 | [47732](https://github.com/airbytehq/airbyte/pull/47732) | Update dependencies |
| 1.4.2 | 2024-10-28 | [47449](https://github.com/airbytehq/airbyte/pull/47449) | Update dependencies |
| 1.4.1 | 2024-08-16 | [44196](https://github.com/airbytehq/airbyte/pull/44196) | Bump source-declarative-manifest version |
| 1.4.0 | 2024-08-15 | [44148](https://github.com/airbytehq/airbyte/pull/44148) | Refactor connector to manifest-only format |
| 1.3.17 | 2024-08-10 | [43545](https://github.com/airbytehq/airbyte/pull/43545) | Update dependencies |
| 1.3.16 | 2024-08-03 | [43254](https://github.com/airbytehq/airbyte/pull/43254) | Update dependencies |
| 1.3.15 | 2024-07-27 | [42663](https://github.com/airbytehq/airbyte/pull/42663) | Update dependencies |
| 1.3.14 | 2024-07-20 | [42382](https://github.com/airbytehq/airbyte/pull/42382) | Update dependencies |
| 1.3.13 | 2024-07-13 | [41713](https://github.com/airbytehq/airbyte/pull/41713) | Update dependencies |
| 1.3.12 | 2024-07-10 | [41423](https://github.com/airbytehq/airbyte/pull/41423) | Update dependencies |
| 1.3.11 | 2024-07-09 | [41311](https://github.com/airbytehq/airbyte/pull/41311) | Update dependencies |
| 1.3.10 | 2024-07-06 | [40941](https://github.com/airbytehq/airbyte/pull/40941) | Update dependencies |
| 1.3.9 | 2024-06-25 | [40381](https://github.com/airbytehq/airbyte/pull/40381) | Update dependencies |
| 1.3.8 | 2024-06-22 | [40177](https://github.com/airbytehq/airbyte/pull/40177) | Update dependencies |
| 1.3.7 | 2024-06-06 | [39173](https://github.com/airbytehq/airbyte/pull/39173) | [autopull] Upgrade base image to v1.2.2 |
| 1.3.6 | 2024-05-15 | [38195](https://github.com/airbytehq/airbyte/pull/38195) | Make connector compatible with builder |
| 1.3.5 | 2024-04-19 | [37162](https://github.com/airbytehq/airbyte/pull/37162) | Updating to 0.80.0 CDK |
| 1.3.4 | 2024-04-18 | [37162](https://github.com/airbytehq/airbyte/pull/37162) | Manage dependencies with Poetry. |
| 1.3.3 | 2024-04-15 | [37162](https://github.com/airbytehq/airbyte/pull/37162) | Base image migration: remove Dockerfile and use the python-connector-base image |
| 1.3.2 | 2024-04-12 | [37162](https://github.com/airbytehq/airbyte/pull/37162) | schema descriptions |
| 1.3.1 | 2024-01-29 | [34633](https://github.com/airbytehq/airbyte/pull/34633) | Add backoff policy for `Requested Items` stream |
| 1.3.0 | 2024-01-15 | [29126](https://github.com/airbytehq/airbyte/pull/29126) | Add `Requested Items` stream |
| 1.2.0 | 2023-08-06 | [29126](https://github.com/airbytehq/airbyte/pull/29126) | Migrated to Low-Code CDK |
| 1.1.0 | 2023-05-09 | [25929](https://github.com/airbytehq/airbyte/pull/25929) | Add stream for customer satisfaction survey responses endpoint |
| 1.0.0 | 2023-05-02 | [25743](https://github.com/airbytehq/airbyte/pull/25743) | Correct data types in tickets, agents and requesters schemas to match Freshservice API |
| 0.1.1 | 2021-12-28 | [9143](https://github.com/airbytehq/airbyte/pull/9143) | Update titles and descriptions |
| 0.1.0 | 2021-10-29 | [6967](https://github.com/airbytehq/airbyte/pull/6967) | 🎉 New Source: Freshservice |

</details>
