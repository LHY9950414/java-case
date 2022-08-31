package com.lhy.config.exception;

import com.alibaba.fastjson.JSON;
import com.lhy.config.common.Result;
import com.lhy.enums.StatusCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

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
    @ExceptionHandler(value = MybatisPlusException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, MybatisPlusException ex){
        StringBuffer requestUrl = req.getRequestURL();
        log.error("自定义异常捕获 requestURL -> {}, msg -> {}, ex -> {}", requestUrl.toString(), ex.getErrorMsg(), JSON.toJSONString(ex));
        return new Result(ex.getErrorCode(), ex.getErrorMsg());
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
        StringBuffer requestUrl = req.getRequestURL();
        log.error("空指针异常捕获 requestURL -> {}, ex -> {}", requestUrl, JSON.toJSONString(ex));
        return new Result(StatusCodeEnum.ERROR.getCode(), StatusCodeEnum.ERROR.getName());
    }

    /**
     * 处理参数校验异常
     * 实体接收参数校验  post MethodArgumentNotValidException，get BindException 需要在controller方法中添加注解 @Validated
     * 单个接收参数 ConstraintViolationException 需要在controller类上添加注解 @Validated
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, Exception ex){
        StringBuffer requestUrl = req.getRequestURL();
        log.error("参数校验异常捕获 requestURL -> {}, ex -> {}", requestUrl.toString(), ex);
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) ex;
            BindingResult bindingResult = me.getBindingResult();
            return getErrorResult(me, bindingResult);
        }
        if (ex instanceof BindException) {
            BindException be = (BindException) ex;
            BindingResult bindingResult = be.getBindingResult();
            return getErrorResult(be, bindingResult);

        }
        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException ce = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> constraintViolations = ce.getConstraintViolations();
            for (ConstraintViolation<?> item : constraintViolations) {
                return new Result(StatusCodeEnum.VALID_ERROR.getCode(), item.getMessage());
            }
        }
        // 其他异常处理
        return new Result(StatusCodeEnum.UNKNOWN.getCode(), StatusCodeEnum.UNKNOWN.getName());
    }

    @Nullable
    private static Result getErrorResult(BindException be, BindingResult bindingResult) {
        if(be.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            if(!CollectionUtils.isEmpty(allErrors)){
                ObjectError objectError = allErrors.get(0);
                return new Result(StatusCodeEnum.VALID_ERROR.getCode(), objectError.getDefaultMessage());
            }
        }
        return null;
    }
}
