package com.example.springbootchatgpt.config.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义业务异常
 * @author liheyan
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChatGptException extends RuntimeException{

    public ChatGptException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 异常码
     */
    private Integer errorCode;
    /**
     * 异常信息
     */
    private String errorMsg;
}
