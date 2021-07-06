package com.zj.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zj.yygh.common.result.Result;
import com.zj.yygh.hosp.service.HospitalSetService;
import com.zj.yygh.model.hosp.HospitalSet;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 赵赳
 * @CreateTime: 2021/7/4 16:50
 * @Description:
 */
@Api("医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    //1 查询医院设置表所有信息
    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("findAll")
    public Result findAllHospitalSet() {
        //调用service的方法
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    //2 逻辑删除医院设置
    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("{id}")
    @ApiParam(name = "id",value = "医院设置id",required = true)
    public Result removeHospSet(@PathVariable Long id) {
        boolean flag = hospitalSetService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //3.添加医院设置
    @ApiOperation(value = "添加或修改医院设置")
    @PostMapping("save")
    @ApiParam(name = "id",value = "医院设置JSONs数据",required = true)
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean isSuccess = hospitalSetService.saveOrUpdate(hospitalSet);
        if (isSuccess) {
            return Result.ok();
        }
        return Result.fail();
    }

    //4.分页查询带条件
    @ApiOperation(value = "分页查询带条件查询医院")
    @GetMapping("list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码数（默认为 1 ）", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "当前页数据长度 （默认为 10 ）", dataType = "long", paramType = "query")
    })
    public Result selectPage(@RequestParam(value = "pageNum", defaultValue = "1") Long pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize) {
        Page<HospitalSet> page = new Page<>(pageNum, pageSize);

        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("is_deleted", 0);

        hospitalSetService.page(page, queryWrapper);

        return Result.ok(page);
    }

    //5.根据id查询
    @ApiOperation(value = "根据id查询医院信息")
    @GetMapping("findById/{id}")
    @ApiParam(name = "id",value = "医院设置id",required = true)
    public Result findById(@PathVariable("id") Long id) {

        HospitalSet hospitalSet = hospitalSetService.getById(id);

        return Result.ok(hospitalSet);
    }


}
