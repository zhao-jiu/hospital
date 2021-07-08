package com.zj.yygh.cmn.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.yygh.cmn.mapper.DictSetMapper;
import com.zj.yygh.cmn.service.DictSetService;
import com.zj.yygh.model.cmn.Dict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 赵赳
 * @CreateTime: 2021/7/4 16:50
 * @Description: 数据字典服务类
 */
@Service
public class DictSetServiceImpl extends ServiceImpl<DictSetMapper, Dict> implements DictSetService {

    @Resource
    private DictSetMapper dictSetMapper;


    @Override
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        List<Dict> dictList = dictSetMapper.selectList(queryWrapper);
        //项每个dict对象设置hasChildren的值
        for (Dict dict : dictList) {
            boolean hasChildren = hasChildren(dict.getId());
            dict.setHasChildren(hasChildren);
        }
        return dictList;
    }

    /**
     * 判断字段数据下面是否有子数据
     *
     * @param id 数据字典id
     * @return 是否有子数据 有 true 没有false
     */
    private boolean hasChildren(Long id) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        Integer count = dictSetMapper.selectCount(queryWrapper);
        return count > 0;
    }
}
