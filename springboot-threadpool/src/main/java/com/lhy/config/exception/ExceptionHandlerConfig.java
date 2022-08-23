package com.lhy.config.exception;

import com.alibaba.fastjson.JSON;
import com.lhy.config.common.Result;
import com.lhy.enums.StatusCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理配置
 * @author liheyan
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerConfig {

    /**
     * 处理自定义异常
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ThreadPoolException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, ThreadPoolException ex){
        StringBuffer requestURL = req.getRequestURL();
        log.error("自定义异常捕获 requestURL -> {}, exception -> {}", requestURL.toString(), JSON.toJSONString(ex));
        return Result.error(ex.getErrorCode(), ex.getErrorMsg());
    }

    /**
     * 处理空指针异常
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NullPointerException ex){
        StringBuffer requestURL = req.getRequestURL();
        log.error("空指针异常捕获 requestURL -> {}, ex -> {}", requestURL, JSON.toJSONString(ex));
        return Result.error(StatusCodeEnum.NULL_ERROR.getCode(), StatusCodeEnum.NULL_ERROR.getName());
    }

    /**
     * 其它异常
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, Exception ex){
        StringBuffer requestURL = req.getRequestURL();
        log.error("其它异常捕获 requestURL -> {}, ex -> {}", requestURL.toString(), JSON.toJSONString(ex));
        return Result.error(StatusCodeEnum.UNKNOWN.getCode(), StatusCodeEnum.UNKNOWN.getName());
    }
}
