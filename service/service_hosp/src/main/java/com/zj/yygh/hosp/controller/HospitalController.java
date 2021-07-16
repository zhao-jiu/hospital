package com.zj.yygh.hosp.controller;

import com.zj.yygh.common.result.Result;
import com.zj.yygh.hosp.service.HospitalService;
import com.zj.yygh.model.hosp.Hospital;
import com.zj.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/15 14:37
 * @Description: 医院管理接口
 */
@Api(tags = "医院管理接口")
@RestController
@RequestMapping("/admin/hosp/hospital")
@CrossOrigin
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    /**
     * 获取医院列表
     */
    @ApiOperation(value = "获取医院列表")
    @PostMapping("list/{page}/{limit}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码数（默认为 1 ）", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "当前页数据长度 （默认为 10 ）", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "hospitalQueryVo", value = "条件查询对象",  paramType = "query")
    })
    public Result<Object> getHospitalList(@PathVariable("page") Integer page,@PathVariable("limit") Integer limit,
                                          @RequestBody(required = false) HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> pages = hospitalService.getHospitalList(page, limit, hospitalQueryVo);

        return Result.ok(pages);
    }

    @ApiOperation(value = "更新医院状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result<Object> updateStatus(@PathVariable("id") String id,@PathVariable("status") Integer status){

        hospitalService.updateStatus(id,status);

        return Result.ok();
    }

    @ApiOperation(value = "获取医院详情信息")
    @GetMapping("getHospDetail/{id}")
    public Result<Object> getHospDetail(@PathVariable("id") String id){

        Map<String,Object> result = hospitalService.getHospDetail(id);

        return Result.ok(result);
    }


}
