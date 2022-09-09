package com.lhy.config.redis;

import lombok.Data;

/**
 * @author liheyan
 */
@Data
public class DelayQueryConfig {

    /**
     * 创建桶的数量
     */
    private Integer jobBucket;

    /**
     * 存放任务队列数据的任务名称
     */
    private String jobName;

    /**
     * 存放桶队列数据的任务名称
     */
    private String bucketName;

    /**
     * redis 缓存key
     */
    private String bucketJobKey;
}
