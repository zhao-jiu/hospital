package com.zj.yygh.hosp.controller.api;

import com.zj.yygh.common.exception.YyghException;
import com.zj.yygh.common.helper.HttpRequestHelper;
import com.zj.yygh.common.result.Result;
import com.zj.yygh.common.result.ResultCodeEnum;
import com.zj.yygh.common.utils.MD5;
import com.zj.yygh.hosp.service.HospitalService;
import com.zj.yygh.hosp.service.HospitalSetService;
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

    @Autowired
    private HospitalSetService hospitalSetService;

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

        //进行签名校验
        String hospSign = (String) paramsMap.get("sign");

        String hoscode = (String) paramsMap.get("hoscode");

        String signKey = hospitalSetService.getByHoscode(hoscode);

        String signKeyMd5 = MD5.encrypt(signKey);

        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        //存储数据到mongodb中
        hospitalService.save(paramsMap);
        return Result.ok();
    }

}
