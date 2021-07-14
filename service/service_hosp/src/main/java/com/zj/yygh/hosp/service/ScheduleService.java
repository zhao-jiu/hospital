package com.zj.yygh.hosp.service;

import com.zj.yygh.model.hosp.Department;
import com.zj.yygh.model.hosp.Schedule;
import com.zj.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/14 16:23
 * @Description:
 */
public interface ScheduleService {
    /**
     * 保存排班信息
     * @param paramsMap
     */
    void save(Map<String, Object> paramsMap);

    /**
     * 查询排班信息
     * @param page
     * @param limit
     * @param queryVo
     * @return
     */
    Page<Schedule> getSchedules(Integer page, Integer limit, ScheduleQueryVo queryVo);

    /**
     * 删除排班信息
     * @param hoscode
     * @param hosScheduleId
     */
    void removeSchedule(String hoscode, String hosScheduleId);
}
