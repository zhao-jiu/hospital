package com.zj.yygh.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/8 10:15
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zj"})
public class ServiceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class,args);
    }
}
