package com.example.mcp;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpServerConfig {

    @Bean
    public ToolCallbackProvider databaseToolCallbackProvider(DatabaseTools databaseTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(databaseTools)
                .build();
    }
}
