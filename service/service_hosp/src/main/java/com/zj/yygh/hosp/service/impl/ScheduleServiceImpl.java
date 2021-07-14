package com.zj.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zj.yygh.hosp.repository.ScheduleRepository;
import com.zj.yygh.hosp.service.ScheduleService;
import com.zj.yygh.model.hosp.Department;
import com.zj.yygh.model.hosp.Schedule;
import com.zj.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/14 16:23
 * @Description:
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    /**
     * 保存排班信息
     * @param paramsMap
     */
    @Override
    public void save(Map<String, Object> paramsMap) {
        String mapString = JSONObject.toJSONString(paramsMap);
        Schedule schedule = JSONObject.parseObject(mapString, Schedule.class);

        Schedule scheduleExist = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(schedule.getHoscode(),schedule.getDepcode());

        if (scheduleExist == null) {
            //添加
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
            schedule.setStatus(1);
        }else {
            scheduleRepository.deleteById(scheduleExist.getId());
            //修改
            schedule.setCreateTime(scheduleExist.getCreateTime());
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
        }
        scheduleRepository.save(schedule);
    }

    /**
     * 查询排班信息
     * @param page
     * @param limit
     * @param queryVo
     * @return
     */
    @Override
    public Page<Schedule> getSchedules(Integer page, Integer limit, ScheduleQueryVo queryVo) {

        //分页对象
        Pageable pageable = PageRequest.of(page-1, limit);

        //将查询条件对象转换为Department对象
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(queryVo,schedule);
        schedule.setIsDeleted(0);
        schedule.setStatus(1);

        //查询条件
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);

        //查询对象
        Example<Schedule> example = Example.of(schedule,exampleMatcher);

        return  scheduleRepository.findAll(example, pageable);
    }

    /**
     * 删除排班信息
     * @param hoscode 医院编码
     * @param hosScheduleId 排班编码
     */
    @Override
    public void removeSchedule(String hoscode, String hosScheduleId) {
        Schedule scheduleExist = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(hoscode,hosScheduleId);
        //删除数据
        if (scheduleExist != null) {
            scheduleRepository.deleteById(scheduleExist.getId());
        }
    }
}
