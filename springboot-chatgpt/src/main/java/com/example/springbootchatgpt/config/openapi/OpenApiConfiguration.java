package com.example.springbootchatgpt.config.openapi;

import com.theokanning.openai.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfiguration {

    private final OpenApiConfig openApiConfig;

    @Bean
    public OpenAiService openAiService(){
        return new OpenAiService(openApiConfig.getKey(), Duration.ofSeconds(openApiConfig.getTimeout()));
    }
}
