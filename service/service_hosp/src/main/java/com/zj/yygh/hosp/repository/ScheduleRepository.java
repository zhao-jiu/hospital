package com.zj.yygh.hosp.repository;

import com.zj.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/14 16:23
 * @Description:
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {
    /**
     * 根据医院编号和排班号查询排班数据
     * @param hoscode 医院编码
     * @param depcode 排班编码
     * @return
     */
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String depcode);
}
