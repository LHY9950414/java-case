package com.lhy.delay.listener;

import com.lhy.common.Constants;
import com.lhy.config.redis.DelayQueryConfig;
import com.lhy.delay.container.DelayBucket;
import com.lhy.delay.container.JobPool;
import com.lhy.delay.handler.DelayJobHandler;
import com.lhy.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author liheyan
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DelayListener implements ApplicationListener<ContextRefreshedEvent> {

    private final DelayBucket delayBucket;
    private final JobPool jobPool;
    private final DelayQueryConfig delayQueryConfig;
    private final RedisUtil redisUtil;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(delayQueryConfig.getJobBucket(), delayQueryConfig.getJobBucket(), 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        //执行任务
        executorService.execute(new DelayJobHandler(delayBucket, jobPool, delayQueryConfig.getJobBucket() - Constants.ONE, redisUtil, delayQueryConfig.getBucketJobKey()));
    }
}
