package com.zj.yygh.easyExcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/9 15:12
 * @Description:  BUG记录，easyExcel 写操作对应的实体类中属性名不能使用驼峰命名
 */
public class WriteTest {

    public static void main(String[] args) {
        //实现excel写的操作
        //1 设置写入文件夹地址和excel文件名称
        String filename = "F:\\testData\\1.xlsx";
        // 2 调用easyexcel里面的方法实现写操作
        // write方法两个参数：第一个参数文件路径名称，第二个参数实体类class
        EasyExcel.write(filename, UserData.class).sheet("学生列表").doWrite(getData());

    }

    //创建方法返回list集合
    private static List<UserData> getData() {
        List<UserData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserData data = new UserData();
            data.setUid(i);
            data.setUname("tom"+i);
            list.add(data);
        }
        return list;
    }
}
