package com.lhy.delay.container;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lhy.common.Constants;
import com.lhy.config.redis.DelayQueryConfig;
import com.lhy.delay.model.DelayJob;
import com.lhy.delay.model.Job;
import com.lhy.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author liheyan
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JobPool {

    private final RedisTemplate redisTemplate;
    private final RedisUtil redisUtil;
    private final DelayQueryConfig delayQueryConfig;
    private final DelayBucket delayBucket;

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
    public void addJob(Job job, DelayJob delayJob, Integer index) {
        log.info("任务池添加任务：{}", JSON.toJSONString(job));
        // 优先存入redis 判断id是否存在，存在则删除后重新放入 ，否则直接存入
        Integer jodId = delayJob.getJodId();
        StringBuffer stringBuffer = new StringBuffer(delayQueryConfig.getBucketJobKey());
        stringBuffer.append(":");
        stringBuffer.append(jodId);
        String str = redisUtil.getStr(stringBuffer.toString());
        if(StringUtils.isEmpty(str)){
            redisUtil.set(stringBuffer.toString(), JSON.toJSONString(delayJob), job.getDelayTime(), TimeUnit.MILLISECONDS);
        } else {
            redisUtil.del(str);
            delayBucket.removeDelayTime(index, JSONObject.parseObject(str, DelayJob.class));
            redisUtil.set(stringBuffer.toString(), JSON.toJSONString(delayJob), job.getDelayTime(), TimeUnit.MILLISECONDS);
        }
        getPool().put(job.getId(), job);
        // 往延时桶队列添加任务
        delayBucket.addDelayJob(delayJob);
    }

    /**
     * 从任务池当中获取任务
     * @param jobId
     * @return
     */
    public Job getJob(Integer jobId) {
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
    public void removeDelayJob (Integer jobId) {
        log.info("任务池移除任务：{}",jobId);
        // 移除任务
        getPool().delete(jobId);
    }

}
