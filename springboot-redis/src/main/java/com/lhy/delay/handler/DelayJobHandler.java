package com.lhy.delay.handler;

import com.alibaba.fastjson.JSON;
import com.lhy.delay.container.DelayBucket;
import com.lhy.delay.container.JobPool;
import com.lhy.delay.model.DelayJob;
import com.lhy.delay.model.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liheyan
 */
@Slf4j
@Data
@AllArgsConstructor
public class DelayJobHandler implements Runnable {

    /**
     * 延时队列
     */
    private DelayBucket delayBucket;
    /**
     * 任务池
     */
    private JobPool jobPool;

    /**
     * 索引
     */
    private Integer index;

    @Override
    public void run() {
        log.info("延时任务开始执行");
        while (true) {
            try {
                DelayJob delayJob = delayBucket.getFirstDelayTime(index);
                //没有任务
                if (delayJob == null) {
                    sleep();
                    continue;
                }
                // 发现延时任务
                // 延时时间没到
                if (delayJob.getDelayDate() > System.currentTimeMillis()) {
                    sleep();
                    continue;
                }
                Job job = jobPool.getJob(delayJob.getJodId());
                //延时任务元数据不存在
                if (job == null) {
                    log.info("移除不存在任务:{}", JSON.toJSONString(delayJob));
                    delayBucket.removeDelayTime(index, delayJob);
                    continue;
                }
                log.info("处理延时任务:{}", JSON.toJSONString(job));
                // 延时任务
                processDelayJob(delayJob, job);
            } catch (Exception e) {
                log.error("扫描DelayBucket出错：{}", e.getStackTrace());
                sleep();
            }
        }
    }

    /**
     * 处理超时任务
     * @param delayJob
     * @param job
     */
    private void processTtrJob(DelayJob delayJob, Job job) {
        // 移除delayBucket中的任务
        delayBucket.removeDelayTime(index, delayJob);
        long delayDate = System.currentTimeMillis();
        delayJob.setDelayDate(delayDate);
        // 再次添加到任务中
        delayBucket.addDelayJob(delayJob);
        jobPool.addJob(job);
    }

    /**
     * 处理延时任务
     * @param delayJob
     * @param job
     */
    private void processDelayJob(DelayJob delayJob, Job job) {
        try {
            // todo 处理任务 ============


            // todo 处理完成任务 移除jobPool中的任务
            jobPool.removeDelayJob(job.getId());
            // todo 移除delayBucket中的任务
            delayBucket.removeDelayTime(index, delayJob);
        } catch (Exception e) {
            // todo 处理异常情况，重新放入队列消费
            processTtrJob(delayJob, job);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            log.error("sleep -> {}", e);
        }
    }
}
