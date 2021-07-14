package com.zj.yygh.hosp.service;


import com.zj.yygh.model.hosp.Department;
import com.zj.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/14 14:34
 * @Description:
 */
public interface DepartmentService {
    /**
     * 保存科室信息
     * @param paramsMap
     */
    void save(Map<String, Object> paramsMap);

    /**
     * 查询科室信息
     * @param page
     * @param limit
     * @param queryVo
     * @return
     */
    Page<Department> getDepartments(Integer page, Integer limit, DepartmentQueryVo queryVo);

    /**
     * 删除科室信息
     * @param hoscode
     * @param depcode
     */
    void removeDepartment(String hoscode, String depcode);
}
