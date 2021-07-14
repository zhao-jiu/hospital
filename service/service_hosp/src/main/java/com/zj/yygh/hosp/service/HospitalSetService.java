package com.zj.yygh.hosp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zj.yygh.model.hosp.HospitalSet;

/**
 * @author 赵赳
 * @CreateTime: 2021/7/4 16:49
 * @Description:
 */
public interface HospitalSetService extends IService<HospitalSet> {

    /**
     * 通过医院编码获取医院签名
     * @param hoscode
     * @return
     */
    String getByHoscode(String hoscode);
}
