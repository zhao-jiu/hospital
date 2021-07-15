package com.zj.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zj.yygh.cmn.client.DictFeignClient;
import com.zj.yygh.hosp.repository.HospitalRepository;
import com.zj.yygh.hosp.service.HospitalService;
import com.zj.yygh.model.hosp.Hospital;
import com.zj.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/13 15:14
 * @Description:
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private DictFeignClient dictFeignClient;

    /**
     * 上传医院信息
     *
     * @param paramsMap 参数集合
     */
    @Override
    public void save(Map<String, Object> paramsMap) {
        //将map转换成Hospital
        String mapStr = JSONObject.toJSONString(paramsMap);
        Hospital hospital = JSONObject.parseObject(mapStr, Hospital.class);

        String hoscode = hospital.getHoscode();
        Hospital hospitalExists = hospitalRepository.getHospitalByHoscode(hoscode);
        //判断是否存在数据
        if (hospitalExists == null) {
            //不存在添加数据
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        } else {
            //先删除数据
            hospitalRepository.deleteById(hospitalExists.getId());
            //存在修改数据
            hospital.setStatus(hospitalExists.getStatus());
            hospital.setCreateTime(hospitalExists.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
    }


    /**
     * 根据医院编码获取医院信息
     *
     * @param hoscode
     * @return
     */
    @Override
    public Hospital getHospByHoscode(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }

    /**
     * 获取医院列表
     */
    @Override
    public Page<Hospital> getHospitalList(Integer pageNum, Integer pageSize, HospitalQueryVo hospitalQueryVo) {
        //分页对象
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        //将查询条件对象转换为Department对象
        Hospital hospital = new Hospital();
        if (hospitalQueryVo != null) {
            BeanUtils.copyProperties(hospitalQueryVo, hospital);
        }
        hospital.setIsDeleted(0);

        //查询条件
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        //查询对象
        Example<Hospital> example = Example.of(hospital, exampleMatcher);
        Page<Hospital> page = hospitalRepository.findAll(example, pageable);

        page.getContent().forEach(item ->{
             setHospitalHosType(item);
        });

        return page;
    }

    /**
     * 设置医院信息
     * @param hospital
     * @return
     */
    private Hospital setHospitalHosType(Hospital hospital) {
        //获取医院等级名称
        String hostypeString = dictFeignClient.getDictName("Hostype", hospital.getHostype());
        //查询 省 市 区
        String provinceString = dictFeignClient.getDictName(hospital.getProvinceCode());
        String cityString = dictFeignClient.getDictName(hospital.getCityCode());
        String districtString = dictFeignClient.getDictName(hospital.getDistrictCode());

        hospital.getParam().put("hostypeString",hostypeString);
        hospital.getParam().put("address",provinceString+cityString+districtString);
        return hospital;
    }






}
