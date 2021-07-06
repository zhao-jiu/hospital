package com.zj.yygh.common.handle;

import com.zj.yygh.common.exception.YyghException;
import com.zj.yygh.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/6 17:38
 * @Description: 全局异常统一处理
 */
@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        return Result.fail();
    }

    @ExceptionHandler(YyghException.class)
    @ResponseBody
    public Result yyghError(YyghException e) {
        return Result.build(e.getCode(),e.getMessage());
    }


}
