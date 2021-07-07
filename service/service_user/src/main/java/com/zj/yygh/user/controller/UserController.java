package com.zj.yygh.user.controller;

import com.zj.yygh.common.result.Result;
import com.zj.yygh.model.acl.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/6 8:24
 * @Description:
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("admin/user")
@CrossOrigin
public class UserController {

    /**
     * 用户登录接口
     *
     * @param user 用户信息
     * @return
     */
    @PostMapping("login")
    @ApiOperation(value = "用户登录")
    public Result<Object> login(@RequestBody User user) {
       return Result.ok();
    }

}
