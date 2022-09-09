package com.lhy.delay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liheyan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DelayJob {


    /**
     * 延时任务的唯一标识
     */
    private Integer jodId;

    /**
     * 任务的执行时间
     */
    private long delayDate;

    /**
     * 任务类型（具体业务类型）
     */
    private String topic;


    public DelayJob(Job job) {
        this.jodId = job.getId();
        this.delayDate = System.currentTimeMillis() + job.getDelayTime();
        this.topic = job.getTopic();
    }


}
