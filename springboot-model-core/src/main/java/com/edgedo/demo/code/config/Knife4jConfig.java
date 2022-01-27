package com.edgedo.demo.code.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author:Qiutianzhu
 * @Date 2022-01-27 16:25
 * @Description: Knife4j配置类
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.knife4j")
public class Knife4jConfig {

    /**
     * swagger请求host
     */
    private String host;

    /**
     * swagger统一前缀
     */
    private String swagerPrefixPath;

}
