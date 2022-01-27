package com.edgedo.demo.code.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Qiutianzhu
 * @Description: 数据库配置信息
 * @Date: 2022/1/27 17:01
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoConfig {

    private String ip;

    private List<String> hosts;

    private String port;

    private String username;

    private String password;

    private String authenticationDatabase;

    private String database;
}
