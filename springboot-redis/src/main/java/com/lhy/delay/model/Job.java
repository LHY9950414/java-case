package com.lhy.delay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job implements Serializable {

    /**
     * 延时任务的唯一标识，用于检索任务
     */
    private String id;

    /**
     * 任务类型（具体业务类型）
     */
    private String topic;

    /**
     * 任务的延时时间
     */
    private Long delayTime;

    /**
     * 任务的执行超时时间
     */
    private Long ttrTime;

    /**
     * 任务具体的消息内容，用于处理具体业务逻辑用
     */
    private String message;
}