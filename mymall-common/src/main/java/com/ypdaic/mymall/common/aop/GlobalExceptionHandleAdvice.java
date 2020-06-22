package com.ypdaic.mymall.common.aop;

import com.ypdaic.mymall.common.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandleAdvice {

    @ExceptionHandler
    public R handelException(Exception e) {
        log.error("未知异常", e);
        return R.error(e.getMessage());
    }
}
