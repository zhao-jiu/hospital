package com.zj.yygh.hosp.controller.api;


import com.zj.yygh.common.exception.YyghException;
import com.zj.yygh.common.helper.HttpRequestHelper;
import com.zj.yygh.common.result.Result;
import com.zj.yygh.common.result.ResultCodeEnum;
import com.zj.yygh.common.utils.MD5;
import com.zj.yygh.hosp.service.DepartmentService;
import com.zj.yygh.hosp.service.HospitalService;
import com.zj.yygh.hosp.service.HospitalSetService;
import com.zj.yygh.hosp.service.ScheduleService;
import com.zj.yygh.model.hosp.Department;
import com.zj.yygh.model.hosp.Hospital;
import com.zj.yygh.model.hosp.Schedule;
import com.zj.yygh.vo.hosp.DepartmentQueryVo;
import com.zj.yygh.vo.hosp.ScheduleQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/13 15:15
 * @Description: 医院信息上传统一Api接口
 */
@RestController
@RequestMapping("api/hosp")
@Slf4j
public class ApiController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalSetService hospitalSetService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * 删除排班信息
     * @param request
     * @return
     */
    @PostMapping("schedule/remove")
    public Result<Object> removeSchedule(HttpServletRequest request) {
        //获取请求参数
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramsMap = HttpRequestHelper.switchMap(requestMap);

        String hoscode = (String) paramsMap.get("hoscode");
        String hosScheduleId = (String) paramsMap.get("hosScheduleId");

        //签名校验 检验失败抛出异常
        verifySign(paramsMap);

        //删除排班信息
        scheduleService.removeSchedule(hoscode,hosScheduleId);
        //日志打印
        log.info("调用地址--->" + request.getRemoteAddr());
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() +
                ": 方法执行了-->请求路径+" + request.getRequestURL().toString());
        return Result.ok();
    }

    /**
     * 查询排班信息
     * @param request
     * @return
     */
    @PostMapping("schedule/list")
    public Result<Object> getSchedules(HttpServletRequest request) {
        //获取请求参数
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramsMap = HttpRequestHelper.switchMap(requestMap);

        //签名校验 检验失败抛出异常
        verifySign(paramsMap);

        Integer page = StringUtils.isEmpty(paramsMap.get("page"))?1:Integer.parseInt((String)paramsMap.get("page"));
        Integer limit = StringUtils.isEmpty(paramsMap.get("limit"))?1:Integer.parseInt((String)paramsMap.get("limit"));
        String hoscode = (String) paramsMap.get("hoscode");

        //签名校验 检验失败抛出异常
        verifySign(paramsMap);

        ScheduleQueryVo queryVo = new ScheduleQueryVo();
        queryVo.setHoscode(hoscode);
        //查询排班信息
        Page<Schedule> pageResult = scheduleService.getSchedules(page,limit,queryVo);
        //日志打印
        log.info("调用地址--->" + request.getRemoteAddr());
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() +
                ": 方法执行了-->请求路径+" + request.getRequestURL().toString());
        return Result.ok(pageResult);
    }


    /**
     * 上传排班信息
     * @param request
     * @return
     */
    @PostMapping("saveSchedule")
    public Result<Object> saveSchedule(HttpServletRequest request) {
        //获取请求参数
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramsMap = HttpRequestHelper.switchMap(requestMap);

        //签名校验 检验失败抛出异常
        verifySign(paramsMap);
        //保存排班信息
        scheduleService.save(paramsMap);
        //日志打印
        //日志打印
        log.info("调用地址--->" + request.getRemoteAddr());
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() +
                ": 方法执行了-->请求路径+" + request.getRequestURL().toString());
        return Result.ok();
    }

    /**
     * 删除科室信息
     * @param request
     * @return
     */
    @PostMapping("department/remove")
    public Result<Object> removeDepartment(HttpServletRequest request) {
        //获取请求参数
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramsMap = HttpRequestHelper.switchMap(requestMap);

        String hoscode = (String) paramsMap.get("hoscode");
        String depcode = (String) paramsMap.get("depcode");

        //签名校验 检验失败抛出异常
        verifySign(paramsMap);

        //删除科室信息
        departmentService.removeDepartment(hoscode,depcode);
        //日志打印
        //日志打印
        log.info("调用地址--->" + request.getRemoteAddr());
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() +
                ": 方法执行了-->请求路径+" + request.getRequestURL().toString());
        return Result.ok();
    }

    /**
     * 查询科室信息
     * @param request
     * @return
     */
    @PostMapping("department/list")
    public Result<Object> getDepartments(HttpServletRequest request) {
        //获取请求参数
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramsMap = HttpRequestHelper.switchMap(requestMap);

        Integer page = StringUtils.isEmpty(paramsMap.get("page"))?1:Integer.parseInt((String)paramsMap.get("page"));
        Integer limit = StringUtils.isEmpty(paramsMap.get("limit"))?1:Integer.parseInt((String)paramsMap.get("limit"));
        String hoscode = (String) paramsMap.get("hoscode");

        //签名校验 检验失败抛出异常
        verifySign(paramsMap);

        DepartmentQueryVo queryVo = new DepartmentQueryVo();
        queryVo.setHoscode(hoscode);
        //查询科室信息
        Page<Department> pageResult = departmentService.getDepartments(page,limit,queryVo);
        //日志打印
        log.info("调用地址--->" + request.getRemoteAddr());
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() +
                ": 方法执行了-->请求路径+" + request.getRequestURL().toString());
        return Result.ok(pageResult);
    }


    /**
     * 保存科室信息
     * @param request
     * @return
     */
    @PostMapping("saveDepartment")
    public Result<Object> saveDepartment(HttpServletRequest request) {
        //获取请求参数
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramsMap = HttpRequestHelper.switchMap(requestMap);

        //签名校验 检验失败抛出异常
        verifySign(paramsMap);

        //保存科室信息
        departmentService.save(paramsMap);
        //日志打印
        log.info("调用地址--->" + request.getRemoteAddr());
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() +
                ": 方法执行了-->请求路径+" + request.getRequestURL().toString());
        return Result.ok();
    }


    /**
     * 查询医院接口
     * @param request
     * @return
     */
    @PostMapping("hospital/show")
    public Result<Object> getHospital(HttpServletRequest request) {
        //获取请求参数
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramsMap = HttpRequestHelper.switchMap(requestMap);

        //签名校验 检验失败抛出异常
        verifySign(paramsMap);

        //根据hoscode查询数据
        String hoscode = (String) paramsMap.get("hoscode");
        Hospital hospital = hospitalService.getHospByHoscode(hoscode);
        //日志打印
        log.info("调用地址--->" + request.getRemoteAddr());
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() +
                ": 方法执行了-->请求路径+" + request.getRequestURL().toString());
        return Result.ok(hospital);
    }

    /**
     * 上传医院接口
     *
     * @param request
     * @return
     */
    @PostMapping("saveHospital")
    public Result<Object> saveHospital(HttpServletRequest request) {
        //获取请求参数
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramsMap = HttpRequestHelper.switchMap(requestMap);
        //图片格式转换
        String logoData = (String) paramsMap.get("logoData");
        logoData = logoData.replaceAll(" ", "+");
        paramsMap.put("logoData", logoData);
        //检验签名
        verifySign(paramsMap);

        //存储数据到mongodb中
        hospitalService.save(paramsMap);
        //日志打印
        log.info("调用地址--->" + request.getRemoteAddr());
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() +
                ": 方法执行了-->请求路径+" + request.getRequestURL().toString());
        return Result.ok();
    }

    /**
     * 签名校验： 检验失败直接抛出异常
     *
     * @param paramsMap
     */
    private void verifySign(Map<String, Object> paramsMap) {
        //参数中的签名
        String hospSign = (String) paramsMap.get("sign");

        //医院编码
        String hoscode = (String) paramsMap.get("hoscode");

        //通过hoscode查询数据库中的签名
        String signKey = hospitalSetService.getByHoscode(hoscode);

        //MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //进行签名校验 失败抛出异常
        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
    }

}
