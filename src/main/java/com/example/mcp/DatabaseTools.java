package com.example.mcp;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DatabaseTools {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseTools(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Tool(description = "List all tables available in the SQLite database")
    public String listTables() {
        try {
            List<Map<String, Object>> tables = jdbcTemplate.queryForList(
                    "SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%'"
            );
            return "Available tables: " + tables.stream()
                    .map(t -> (String) t.get("name"))
                    .collect(Collectors.joining(", "));
        } catch (Exception e) {
            return "Error listing tables: " + e.getMessage();
        }
    }

    @Tool(description = "Describe the schema of a specific table in the database")
    public String describeTable(String tableName) {
        try {
            List<Map<String, Object>> columns = jdbcTemplate.queryForList("PRAGMA table_info(" + tableName + ")");
            if (columns.isEmpty()) {
                return "Table '" + tableName + "' not found or has no columns.";
            }
            return columns.stream()
                    .map(col -> col.get("name") + " (" + col.get("type") + ")")
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "Error describing table " + tableName + ": " + e.getMessage();
        }
    }

    @Tool(description = "Execute a read-only SELECT query against the database and return results as JSON string")
    public String runSelectQuery(String sql) {
        if (!sql.trim().toUpperCase().startsWith("SELECT")) {
            return "Error: Only SELECT queries are allowed.";
        }
        try {
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
            if (results.isEmpty()) {
                return "Query returned 0 results.";
            }
            // Simple toString representation, in a real app use Jackson ObjectMapper
            return results.toString();
        } catch (Exception e) {
            return "Error executing query: " + e.getMessage();
        }
    }
}
