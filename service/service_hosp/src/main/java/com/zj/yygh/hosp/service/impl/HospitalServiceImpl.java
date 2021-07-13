package com.zj.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zj.yygh.hosp.repository.HospitalRepository;
import com.zj.yygh.hosp.service.HospitalService;
import com.zj.yygh.model.hosp.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/13 15:14
 * @Description:
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    /**
     * 上传医院信息
     * @param paramsMap 参数集合
     */
    @Override
    public void save(Map<String, Object> paramsMap) {
        //将map转换成Hospital
        String mapStr = JSONObject.toJSONString(paramsMap);
        Hospital hospital = JSONObject.parseObject(mapStr, Hospital.class);

        String hoscode = hospital.getHoscode();
        Hospital hospitalExists = hospitalRepository.getHospitalByHoscode(hoscode);
        //判断是否存在数据
        if (hospitalExists == null) {
            //不存在添加数据
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }else {
            //存在修改数据
            hospital.setStatus(hospitalExists.getStatus());
            hospital.setCreateTime(hospitalExists.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }




    }
}
