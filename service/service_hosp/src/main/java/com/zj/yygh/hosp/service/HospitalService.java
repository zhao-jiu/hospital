package com.zj.yygh.hosp.service;

import com.zj.yygh.model.hosp.Hospital;
import com.zj.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/13 15:14
 * @Description:
 */
public interface HospitalService {
    /**
     * 上传医院信息
     * @param paramsMap 参数集合
     */
    void save(Map<String, Object> paramsMap);

    /**
     * 根据医院编码获取医院信息
     * @param hoscode
     * @return
     */
    Hospital getHospByHoscode(String hoscode);

    /**
     * 获取医院列表
     */
    Page<Hospital> getHospitalList(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    /**
     * 更新医院状态
     * @param id
     * @param status
     */
    void updateStatus(String id, Integer status);

    /**
     * 获取医院详情信息
     * @param id
     * @return
     */
    Map<String, Object> getHospDetail(String id);
}
