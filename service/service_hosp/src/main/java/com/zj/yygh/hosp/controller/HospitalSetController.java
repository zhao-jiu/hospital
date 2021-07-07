package com.zj.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zj.yygh.common.result.Result;
import com.zj.yygh.common.utils.MD5;
import com.zj.yygh.hosp.service.HospitalSetService;
import com.zj.yygh.model.hosp.HospitalSet;
import com.zj.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @author 赵赳
 * @CreateTime: 2021/7/4 16:50
 * @Description: 医院设置管理接口
 */
@Api(tags = "医院设置管理接口")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    /**
     * 查询医院设置表所有信息
     *
     * @return
     */
    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("findAll")
    public Result<Object> findAllHospitalSet() {
        //调用service的方法
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    /**
     * 逻辑删除医院设置
     *
     * @param id 医院设置id
     * @return
     */
    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("{id}")
    @ApiParam(name = "id", value = "医院设置id", required = true)
    public Result removeHospSet(@PathVariable Long id) {
        boolean flag = hospitalSetService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 添加医院设置
     *
     * @param hospitalSet 医院设置JSON数据
     * @return
     */
    @ApiOperation(value = "添加医院设置")
    @PostMapping("save")
    @ApiParam(name = "hospitalSet", value = "医院设置JSON数据", required = true)
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        String encrypt = MD5.encrypt(System.currentTimeMillis() + (new Random().nextInt(1000) + ""));
        //签名秘钥
        hospitalSet.setSignKey(encrypt);
        boolean isSuccess = hospitalSetService.save(hospitalSet);
        if (isSuccess) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 修改医院设置
     *
     * @param hospitalSet 医院设置JSON数据
     * @return
     */
    @ApiOperation(value = "修改医院设置")
    @PutMapping("update")
    @ApiParam(name = "hospitalSet", value = "修改医院设置", required = true)
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean isSuccess = hospitalSetService.updateById(hospitalSet);
        if (isSuccess) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 分页查询带条件
     *
     * @param pageNum  当前页码
     * @param pageSize 分页条数
     * @return
     */
    @ApiOperation(value = "分页查询带条件查询医院")
    @PostMapping("queryByParams")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码数（默认为 1 ）", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "当前页数据长度 （默认为 10 ）", dataType = "long", paramType = "query")
    })
    public Result selectPage(@RequestParam(value = "pageNum", defaultValue = "1") Long pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize,
                             @RequestBody(required = false) HospitalSetQueryVo vo) {
        Page<HospitalSet> page = new Page<>(pageNum, pageSize);

        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("is_deleted", 0);

        //根据医院代码匹配查询
        if (!StringUtils.isEmpty(vo.getHoscode())) {
            queryWrapper.eq("hoscode", vo.getHoscode());
        }
        //根据医院名称模糊查询
        if (!StringUtils.isEmpty(vo.getHosname())) {
            queryWrapper.like("hosname", vo.getHosname());
        }
        //未锁定状态
        queryWrapper.eq("status",0);
        hospitalSetService.page(page, queryWrapper);

        return Result.ok(page);
    }

    /**
     * 根据id查询
     *
     * @param id 医院设置id
     * @return
     */
    @ApiOperation(value = "根据id查询医院信息")
    @GetMapping("findById/{id}")
    @ApiParam(name = "id", value = "医院设置id", required = true)
    public Result findById(@PathVariable("id") Long id) {

        HospitalSet hospitalSet = hospitalSetService.getById(id);

        return Result.ok(hospitalSet);
    }

    /**
     * 批量删除医院设置信息
     *
     * @param ids 医院设置id数组
     * @return
     */
    @ApiOperation(value = "批量删除医院设置信息")
    @DeleteMapping("deleteBatch")
    @ApiParam(name = "ids", value = "医院设置id数组", required = true)
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean isSuccess = hospitalSetService.removeByIds(ids);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 锁定医院信息
     *
     * @param id 医院设置id
     * @param status
     * @return
     */
    @PutMapping("lock/{id}/{status}")
    @ApiOperation(value = "锁定医院信息")
    public Result lock(@PathVariable Long id,
                       @PathVariable Integer status) {

        HospitalSet hospitalSet = hospitalSetService.getById(id);

        hospitalSet.setStatus(status);

        hospitalSetService.updateById(hospitalSet);

        return Result.ok();
    }

    /**
     * 发送签名秘钥
     *
     * @param id 医院设置id
     * @return
     */
    @PutMapping("sendKey/{id}")
    @ApiOperation(value = "发送签名秘钥")
    public Result sendKey(@PathVariable Long id) {

        HospitalSet hospitalSet = hospitalSetService.getById(id);

        String hoscode = hospitalSet.getHoscode();
        String signKey = hospitalSet.getSignKey();

        //TODO 发送短信
        return Result.ok();
    }


}
