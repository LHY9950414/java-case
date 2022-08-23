package com.lhy.config.threadpool;

import lombok.Data;

/**
 * 获取线程配置
 * @author liheyan
 */
@Data
public class AsyncThreadProperties {

    private int corePoolSize;

    private int maxPoolSize;

    private int queueCapacity;

}
