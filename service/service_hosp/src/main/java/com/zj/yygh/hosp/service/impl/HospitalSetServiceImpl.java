package com.zj.yygh.hosp.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.yygh.hosp.mapper.HospitalSetMapper;
import com.zj.yygh.hosp.service.HospitalSetService;
import com.zj.yygh.model.hosp.HospitalSet;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 赵赳
 * @CreateTime: 2021/7/4 16:50
 * @Description:
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    @Resource
    private HospitalSetMapper hospitalSetMapper;

    /**
     * 通过医院编码获取医院签名
     * @param hoscode
     * @return
     */
    @Override
    public String getByHoscode(String hoscode) {
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(queryWrapper);
        return hospitalSet.getSignKey();
    }
}
