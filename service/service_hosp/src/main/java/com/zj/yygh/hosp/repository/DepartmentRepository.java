package com.zj.yygh.hosp.repository;

import com.zj.yygh.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/14 14:34
 * @Description:
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {
    /**
     * 根据医院编码和科室编码查询科室信息
     * @param hoscode
     * @param depcode
     * @return
     */
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);
}
