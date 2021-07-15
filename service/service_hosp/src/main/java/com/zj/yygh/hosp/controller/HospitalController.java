package com.zj.yygh.hosp.controller;

import com.zj.yygh.common.result.Result;
import com.zj.yygh.hosp.service.HospitalService;
import com.zj.yygh.model.hosp.Hospital;
import com.zj.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/15 14:37
 * @Description:
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
    @PostMapping("list/{pageNum}/{pageSize}")
    public Result<Object> getHospitalList(@PathVariable("pageNum") Integer pageNum,
                                          @PathVariable("pageSize") Integer pageSize,
                                          @RequestBody(required = false) HospitalQueryVo hospitalQueryVo) {

        Page<Hospital> page = hospitalService.getHospitalList(pageNum, pageSize, hospitalQueryVo);

        return Result.ok(page);
    }

}
