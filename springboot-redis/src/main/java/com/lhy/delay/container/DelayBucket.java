package com.lhy.delay.container;

import com.alibaba.fastjson.JSON;
import com.lhy.common.Constants;
import com.lhy.config.redis.DelayQueryConfig;
import com.lhy.delay.model.DelayJob;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liheyan
 * @author liheyan
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DelayBucket {

    private final RedisTemplate redisTemplate;
    private final DelayQueryConfig delayQueryConfig;
    private static AtomicInteger index = new AtomicInteger(0);

    private List<String> bucketNames = new ArrayList<>();

    /**
     * 创建 Delay bucket
     */
    @Bean
    public List<String> createBuckets() {
        for (int i = 1; i <= delayQueryConfig.getJobBucket(); i++) {
            bucketNames.add(delayQueryConfig.getBucketName() + i);
        }
        return bucketNames;
    }

    /**
     * 获得桶的名称
     *
     * @return
     */
    private String getThisBucketName() {
        int thisIndex = index.addAndGet(Constants.ONE);
        Integer i = thisIndex % delayQueryConfig.getJobBucket();
        return bucketNames.get(i);
    }

    /**
     * 获得桶集合
     *
     * @param bucketName
     * @return
     */
    private BoundZSetOperations getBucket(String bucketName) {
        return redisTemplate.boundZSetOps(bucketName);
    }

    /**
     * 放入延时任务
     *
     * @param job
     */
    public void addDelayJob(DelayJob job) {
        log.info("添加延时任务:{}", JSON.toJSONString(job));
        String thisBucketName = getThisBucketName();
        BoundZSetOperations bucket = getBucket(thisBucketName);
        bucket.add(job, job.getDelayDate());
    }

    /**
     * 获得最新的延期任务
     *
     * @return
     */
    public DelayJob getFirstDelayTime(Integer index) {
        String name = bucketNames.get(index);
        BoundZSetOperations bucket = getBucket(name);
        //获取排名最靠前的成员
        Set<ZSetOperations.TypedTuple> set = bucket.rangeWithScores(0, 1);
        if (CollectionUtils.isEmpty(set)) {
            return null;
        }
        ZSetOperations.TypedTuple typedTuple = (ZSetOperations.TypedTuple) set.toArray()[0];
        Object value = typedTuple.getValue();
        if (value instanceof DelayJob) {
            return (DelayJob) value;
        }
        return null;
    }

    /**
     * 移除延时任务
     *
     * @param index
     * @param delayJob
     */
    public void removeDelayTime(Integer index, DelayJob delayJob) {
        String name = bucketNames.get(index);
        BoundZSetOperations bucket = getBucket(name);
        bucket.remove(delayJob);
    }

}
