package com.ci.controller;

import com.ci.exception.BusinessException;
import com.ci.exception.SystemException;
import com.ci.pojo.vo.ErrorCode;
import com.ci.pojo.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException e) {
        return new Result(e.getCode(), null, e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException e) {
        return new Result(e.getCode(), null, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception e) {
        return new Result(ErrorCode.UNKNOW_ERR, null, "未知错误");
    }
}
