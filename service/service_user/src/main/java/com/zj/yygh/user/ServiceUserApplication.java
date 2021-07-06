package com.zj.yygh.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 赵赳
 * @CreateTime: 2021/7/4 16:19
 * @Description:
 */
@SpringBootApplication
@ComponentScan("com.zj.yygh")
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }
}
