package com.zj.yygh.easyExcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/9 15:10
 * @Description:
 */
@Data
public class UserData {

    @ExcelProperty(value = "用户编号",index = 0)
    private Integer uid;

    @ExcelProperty(value = "用户名称",index = 1)
    private String uname;
}
