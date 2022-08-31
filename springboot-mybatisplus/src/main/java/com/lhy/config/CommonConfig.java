package com.lhy.config;

import com.lhy.config.threadpool.AsyncThreadProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liheyan
 */
@Configuration
public class CommonConfig {

    @Bean
    @ConfigurationProperties(prefix = "thread-pool")
    public AsyncThreadProperties getFocusUserConfig() {
        return new AsyncThreadProperties();
    }

}
