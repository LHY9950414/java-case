package com.example.springbootchatgpt.config;

import com.example.springbootchatgpt.config.openapi.OpenApiConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liheyan
 */
@Configuration
public class CommonConfig {

    @Bean
    @ConfigurationProperties(prefix = "open.ai")
    public OpenApiConfig getOpenAi() {
        return new OpenApiConfig();
    }

}
