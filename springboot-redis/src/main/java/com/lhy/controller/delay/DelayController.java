package com.lhy.controller.delay;

import com.alibaba.fastjson.JSON;
import com.lhy.common.Constants;
import com.lhy.config.redis.DelayQueryConfig;
import com.lhy.delay.container.DelayBucket;
import com.lhy.delay.container.JobPool;
import com.lhy.delay.model.DelayJob;
import com.lhy.delay.model.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liheyan
 */
@RestController
@RequestMapping("/api/delay")
@RequiredArgsConstructor
public class DelayController {
    private final JobPool jobPool;
    private final DelayQueryConfig delayQueryConfig;

    /**
     * 添加
     * @param job
     * @return
     */
    @PostMapping(value = "/add")
    public String addDefJob(@RequestBody Job job) {
        job.setTopic("order");
        // 延时执行的时间 2 分钟
//        job.setDelayTime(120000L);

        // 创建任务引用对象 (计算触发执行的具体时间)
        DelayJob delayJob = new DelayJob(job);
        // 往任务池当中添加任务
        jobPool.addJob(job, delayJob, delayQueryConfig.getJobBucket() - Constants.ONE);
        return JSON.toJSONString(delayJob);
    }
}
