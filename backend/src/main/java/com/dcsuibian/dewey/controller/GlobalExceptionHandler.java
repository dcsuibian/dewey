package com.dcsuibian.dewey.controller;

import com.dcsuibian.dewey.exception.BusinessException;
import com.dcsuibian.dewey.vo.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseWrapper<Void> handleBusinessException(BusinessException e) {
        log.debug("全局异常处理中心：{}", e.getMessage());
        return ResponseWrapper.fail(e.getMessage(), e.getCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseWrapper<Void> handleException(Exception e) {
        String message = null != e.getMessage() ? e.getMessage() : e.getClass().getName();
        log.warn("全局异常处理中心：{}", message, e);
        return ResponseWrapper.fail(message, 500);
    }
}
