package com.lhy.common;

import com.lhy.enums.StatusCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liheyan
 */
@Data
public class Result<T> implements Serializable {
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 成功，无返回数据
     * @return
     */
    public static Result success() {
        return new Result();
    }

    /**
     * 成功有返回数据
     * @param data
     * @return
     */
    public static Result success(Object data) {
        return new Result(data);
    }

    /**
     * 成功自定义返回状态码 && 消息
     * @param code
     * @param message
     * @return
     */
    public static Result success(Integer code, String message) {
        return new Result(code, message);
    }

    /**
     * 成功自定义返回状态码 && 消息 && 数据
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static Result success(Integer code, String message, Object data) {
        return new Result(code, message, data);
    }

    /**
     * 失败，默认500，服务器内部错误
     * @return
     */
    public static Result error() {
        return new Result(StatusCodeEnum.ERROR.getCode(), StatusCodeEnum.ERROR.getName());
    }

    /**
     * 失败，自定义返回状态码 && 消息
     * @param code
     * @param message
     * @return
     */
    public static Result error(Integer code, String message) {
        return new Result(code, message);
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = (T) data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Object data) {
        this();
        this.data = (T) data;
    }

    public Result() {
        this.code = StatusCodeEnum.OK.getCode();
        this.message = StatusCodeEnum.OK.getName();
    }
}
