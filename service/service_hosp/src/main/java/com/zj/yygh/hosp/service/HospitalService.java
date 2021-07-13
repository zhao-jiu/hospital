package com.zj.yygh.hosp.service;

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
}
