package com.lhy.config.exception;

import lombok.Data;

/**
 * 自定义业务异常
 * @author liheyan
 */
@Data
public class ThreadPoolException extends RuntimeException{

    public ThreadPoolException(Integer errorCode, String errorMsg) {
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
