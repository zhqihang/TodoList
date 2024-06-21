package com.qihang.todo.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.qihang.todo.common.api.Result;
import com.qihang.todo.common.api.ResultEnum;

// 开启全局异常捕获和返回Json @ResponseBody + @ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义异常
     * @return Result
     * @ExceptionHandler 说明捕获哪些异常，对那些异常进行处理。
     */
    @ExceptionHandler(DefinitionException.class)
    public Result<Object> customException(DefinitionException e) {
        return Result.defineError(e);
    }

    /**
     * 处理其他异常
     * @return Result
     */
    @ExceptionHandler(value = Exception.class)
    public Result<Object> exceptionHandler(Exception e) {
        // return Result.otherError(ResultEnum.INTERNAL_SERVER_ERROR);
        return Result.otherError(ResultEnum.INTERNAL_SERVER_ERROR);
    }
}
