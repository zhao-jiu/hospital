package com.zj.yygh.common.handler;

import com.zj.yygh.common.exception.YyghException;
import com.zj.yygh.common.result.Result;
import com.zj.yygh.common.result.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/6 17:38
 * @Description: 全局异常统一处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail(ResultCodeEnum.SERVICE_ERROR);
    }

    /**
     * 自定义异常处理
     * @param e 自定义异常
     * @return
     */
    @ExceptionHandler(YyghException.class)
    @ResponseBody
    public Result yyghError(YyghException e) {
        return Result.build(e.getCode(),e.getMessage());
    }
}
