package com.zj.yygh.cmn.controller;

import com.zj.yygh.cmn.service.DictService;
import com.zj.yygh.common.result.Result;
import com.zj.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/6 8:24
 * @Description:
 */
@Api(tags = "数据字典管理接口")
@RestController
@RequestMapping("admin/cmn/dict")
@CrossOrigin
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * 导入数据字典数据
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("importData")
    @ApiOperation(value = "导入数据字典接口")
    public Result importDict(MultipartFile file) throws IOException {

        dictService.importDictData(file);

        return Result.ok();
    }


    /**
     * 导出数据字典接口
     * @param response
     */
    @GetMapping("exportData")
    @ApiOperation(value = "导出数据字典接口")
    public void exportDict(HttpServletResponse response){

        dictService.exportDictData(response);

    }

    /**
     * 根据数据id查询子数据列表
     *
     * @param id 字典数据id
     * @return
     */
    @GetMapping("findChildData/{id}")
    @ApiOperation(value = "根据数据id查询子数据列表")
    @ApiParam(name = "id", value = "字典数据id", required = true)
    public Result findChildData(@PathVariable Long id) {

        List<Dict> list = dictService.findChildData(id);

        return Result.ok(list);
    }
}
