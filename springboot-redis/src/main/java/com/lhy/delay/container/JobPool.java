package com.lhy.delay.container;

import com.alibaba.fastjson.JSON;
import com.lhy.config.redis.DelayQueryConfig;
import com.lhy.delay.model.Job;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author liheyan
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JobPool {

    private final RedisTemplate redisTemplate;
    private final DelayQueryConfig delayQueryConfig;

    /**
     * 获取任务池
     */
    private BoundHashOperations getPool() {
        BoundHashOperations ops = redisTemplate.boundHashOps(delayQueryConfig.getJobName());
        return ops;
    }

    /**
     * 添加任务
     *
     * @param job
     */
    public void addJob(Job job) {
        log.info("任务池添加任务：{}", JSON.toJSONString(job));
        getPool().put(job.getId(), job);
    }

    /**
     * 从任务池当中获取任务
     * @param jobId
     * @return
     */
    public Job getJob(String jobId) {
        Object o = getPool().get(jobId);
        if (o instanceof Job) {
            return (Job) o;
        }
        return null;
    }

    /**
     * 从任务池中移除任务
     * @param jobId
     */
    public void removeDelayJob (String jobId) {
        log.info("任务池移除任务：{}",jobId);
        // 移除任务
        getPool().delete(jobId);
    }

}
