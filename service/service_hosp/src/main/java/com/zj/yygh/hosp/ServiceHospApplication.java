package com.zj.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 赵赳
 * @CreateTime: 2021/7/4 16:19
 * @Description:  踩坑日记：@EnableFeignClients(basePackages = {"com.zj"}) 找不到对应的feign接口
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zj"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.zj"})
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}
