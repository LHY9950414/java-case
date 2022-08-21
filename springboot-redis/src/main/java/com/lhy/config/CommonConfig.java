package com.lhy.config;

import com.lhy.config.redis.DelayQueryConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liheyan
 */
@Configuration
public class CommonConfig {

    @Bean
    @ConfigurationProperties(prefix = "redis.delay-query")
    public DelayQueryConfig getFocusUserConfig() {
        return new DelayQueryConfig();
    }

}
