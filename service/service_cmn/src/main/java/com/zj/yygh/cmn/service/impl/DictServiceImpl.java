package com.zj.yygh.cmn.service.impl;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.yygh.cmn.listener.DictListener;
import com.zj.yygh.cmn.mapper.DictMapper;
import com.zj.yygh.cmn.service.DictService;
import com.zj.yygh.model.cmn.Dict;
import com.zj.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵赳
 * @CreateTime: 2021/7/4 16:50
 * @Description: 数据字典服务类
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    private DictMapper dictMapper;

    /**
     * 使用redis缓存数据
     * @Cacheable 开启缓存
     * @param id
     * @return
     */
    @Cacheable(value = "dict", keyGenerator = "keyGenerator")
    @Override
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        List<Dict> dictList = dictMapper.selectList(queryWrapper);
        //项每个dict对象设置hasChildren的值
        for (Dict dict : dictList) {
            boolean hasChildren = hasChildren(dict.getId());
            dict.setHasChildren(hasChildren);
        }
        return dictList;
    }

    /**
     * 导出字典数据
     * @param response
     */
    @Override
    public void exportDictData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("数据字典", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");

            List<Dict> list = dictMapper.selectList(null);

            ArrayList<DictEeVo> dictEeVos = new ArrayList<>();
            //将字典数据转换到VO中
            for (Dict dict : list) {
                DictEeVo dictEeVo = new DictEeVo();
                BeanUtils.copyProperties(dict,dictEeVo);
                dictEeVos.add(dictEeVo);
            }
            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("数据字典")
                    .doWrite(dictEeVos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * allEntries = true 清空缓存中的数据
     */
    @CacheEvict(value = "dict",allEntries = true)
    @Override
    public void importDictData(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListener(baseMapper)).sheet().doRead();
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
        Integer count = dictMapper.selectCount(queryWrapper);
        return count > 0;
    }
}
