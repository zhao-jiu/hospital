package com.zj.yygh.cmn.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zj.yygh.model.cmn.Dict;
import com.zj.yygh.model.hosp.HospitalSet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 赵赳
 * @CreateTime: 2021/7/4 16:49
 * @Description:
 */
public interface DictService extends IService<Dict> {

    /**
     * 根据数据id查询子数据列表
     * @param id
     * @return
     */
    List<Dict> findChildData(Long id);

    /**
     * 导出数据字典数据
     * @param response
     */
    void exportDictData(HttpServletResponse response);

    /**
     * 导入数据字典数据
     * @param file
     */
    void importDictData(MultipartFile file) throws IOException;
}
