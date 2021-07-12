package com.zj.yygh.easyExcel;

import com.alibaba.excel.EasyExcel;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/9 15:47
 * @Description:
 */
public class ReadTest {
    public static void main(String[] args) {

        //实现excel读操作
        String filename = "F:\\testData\\1.xlsx";
        EasyExcel.read(filename, UserData.class,new ExcelListener()).sheet().doRead();
    }
}
