package com.edgedo.framework;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author:Qiutianzhu
 * @Date 2022-01-27 13:51
 * @Description: 主程序启动入口
 */
@SpringBootApplication
@ComponentScan("com.edgedo")
@EnableAsync
public class SpringBootTempApplication {
    public static void main(String[] args) {
        //使用建造器启动app //可使用SpringApplication.run(AppStarter.class,args)简单启动
        new SpringApplicationBuilder(SpringBootTempApplication.class)
                .run(args);
    }
}
