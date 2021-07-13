package com.zj.yygh.hosp.controller.api;

import com.zj.yygh.common.helper.HttpRequestHelper;
import com.zj.yygh.common.result.Result;
import com.zj.yygh.hosp.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/13 15:15
 * @Description:
 */
@RestController
@RequestMapping("api/hosp")
public class HospitalApiController {

    @Autowired
    private HospitalService hospitalService;

    /**
     * 上传医院接口
     *
     * @param request
     * @return
     */
    @PostMapping("saveHospital")
    public Result saveHospital(HttpServletRequest request) {
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramsMap = HttpRequestHelper.switchMap(requestMap);
        hospitalService.save(paramsMap);
        return Result.ok();
    }

}
