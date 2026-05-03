# Java MCP SQLite Server

A Spring Boot application that implements the Model Context Protocol (MCP) server for interacting with an SQLite database. It leverages Spring AI (`spring-ai-starter-mcp-server-webmvc`) to expose a set of database query tools to AI clients via the MCP standard.

## Features

This server exposes the following tools to any connected MCP client:

- **listTables**: Lists all available tables in the SQLite database.
- **describeTable**: Describes the schema of a specific table, including column names and types.
- **runSelectQuery**: Executes a read-only `SELECT` query against the database and returns the results as a JSON-like string.

## Prerequisites

- **Java 21** or later
- **Maven** (optional, as the project might use Maven wrapper if included, or you can use your local Maven installation)
- **SQLite** (optional to view the database externally, as the driver is embedded)

## Configuration

The application is configured via `src/main/resources/application.properties`:

- The SQLite database is created locally at the project root as `mcp_demo.db`.
- The server runs on port `8080` by default.
- MCP Logging is set to `DEBUG` to easily see the interactions in the console.

## How to Run

1. Clone the repository and navigate to the root directory.
2. Run the Spring Boot application using Maven:

   ```bash
   mvn spring-boot:run
   ```

3. The server will start, initializing the SQLite database (`mcp_demo.db` will be created if it doesn't exist) and opening the MCP web endpoints on `localhost:8080`.

## Connecting an MCP Client

Because this project uses the `spring-ai-starter-mcp-server-webmvc` dependency, the MCP server will typically be available via Server-Sent Events (SSE). An MCP client can connect to this server by configuring the endpoint URL (usually `http://localhost:8080/mcp/sse` depending on standard Spring AI defaults) and interact with the registered database tools.
