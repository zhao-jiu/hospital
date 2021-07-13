package com.zj.yygh.hosp.repository;

import com.zj.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/13 15:11
 * @Description:
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {
    /**
     * 根据医院编码查询数据
     * @param hoscode 医院编码
     * @return 医院信息
     */
    Hospital getHospitalByHoscode(String hoscode);
}
