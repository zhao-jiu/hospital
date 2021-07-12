package com.zj.yygh.easyExcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/1 16:49
 * @Description:
 */
public class ExcelListener extends AnalysisEventListener<UserData> {

    //一行一行读取excel内容
    @Override
    public void invoke(UserData data, AnalysisContext analysisContext) {
        System.out.println("表格数据："+data);
    }
    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
    }
    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) { }
}
