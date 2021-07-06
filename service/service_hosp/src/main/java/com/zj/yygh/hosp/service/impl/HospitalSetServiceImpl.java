package com.zj.yygh.hosp.service.impl;


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


}
