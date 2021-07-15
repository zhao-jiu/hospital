package com.zj.yygh.cmn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/15 15:31
 * @Description:
 */
@FeignClient(value = "service-cmn")
@Component
public interface DictFeignClient {

    /**
     * 根据dictCode和value查询字典名称
     * @param dictCode
     * @param value
     * @return
     */
    @GetMapping("admin/cmn/dict/getDictName/{dictCode}/{value}")
    public String getDictName(@PathVariable("dictCode") String dictCode, @PathVariable("value") String value);

    /**
     * 根据dictCode和value查询字典名称
     * @param value
     * @return
     */
    @GetMapping ("admin/cmn/dict/getDictName/{value}")
    public String getDictName(@PathVariable("value") String value);


}
