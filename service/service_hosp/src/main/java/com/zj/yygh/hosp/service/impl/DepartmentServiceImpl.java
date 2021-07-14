package com.zj.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zj.yygh.hosp.repository.DepartmentRepository;
import com.zj.yygh.hosp.service.DepartmentService;
import com.zj.yygh.model.hosp.Department;
import com.zj.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/14 14:35
 * @Description:
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * 保存科室信息
     * @param paramsMap
     */
    @Override
    public void save(Map<String, Object> paramsMap) {
        String mapString = JSONObject.toJSONString(paramsMap);
        Department department = JSONObject.parseObject(mapString, Department.class);

        Department departmentExist =departmentRepository.getDepartmentByHoscodeAndDepcode(department.getHoscode(),department.getDepcode());

        if (departmentExist == null) {
            //添加
            department.setCreateTime(new Date());
            department.setCreateTime(new Date());
        }else {
            departmentRepository.deleteById(departmentExist.getId());
            //修改
            department.setUpdateTime(new Date());
            department.setCreateTime(departmentExist.getCreateTime());
        }
        department.setIsDeleted(0);
        departmentRepository.save(department);
    }

    /**
     * 查询科室信息
     * @param page
     * @param limit
     * @param queryVo
     * @return
     */
    @Override
    public Page<Department> getDepartments(Integer page, Integer limit, DepartmentQueryVo queryVo) {

        //分页对象
        Pageable pageable =PageRequest.of(page-1, limit);

        //将查询条件对象转换为Department对象
        Department department = new Department();
        BeanUtils.copyProperties(queryVo,department);
        department.setIsDeleted(0);

        //查询条件
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        //查询对象
        Example<Department> example = Example.of(department,exampleMatcher);

        return departmentRepository.findAll(example, pageable);
    }

    /**
     * 删除科室信息
     * @param hoscode 医院编码 唯一
     * @param depcode 科室编码 唯一
     */
    @Override
    public void removeDepartment(String hoscode, String depcode) {
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        //有数据删除
        if (department != null) {
            departmentRepository.deleteById(department.getId());
        }
    }



}
