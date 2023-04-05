package com.example.springbootchatgpt.config.openapi;

import lombok.Data;

/**
 * @author liheyan
 */
@Data
public class OpenApiConfig {
    private String model;
    private long timeout;
    private String key;
}
