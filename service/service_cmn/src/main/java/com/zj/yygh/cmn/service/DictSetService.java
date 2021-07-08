package com.zj.yygh.cmn.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zj.yygh.model.cmn.Dict;
import com.zj.yygh.model.hosp.HospitalSet;

import java.util.List;

/**
 * @author 赵赳
 * @CreateTime: 2021/7/4 16:49
 * @Description:
 */
public interface DictSetService extends IService<Dict> {

    /**
     * 根据数据id查询子数据列表
     * @param id
     * @return
     */
    List<Dict> findChildData(Long id);
}
