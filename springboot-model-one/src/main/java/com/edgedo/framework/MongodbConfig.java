package com.edgedo.framework;

import com.edgedo.demo.code.config.MongoConfig;
import com.edgedo.demo.common.DatabaseUtils;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author:Qiutianzhu
 * @Date: 2021/2/23 15:26
 * @Description: 标签模块graph-model-tag下的Mongodb配置
 **/
@Configuration
@EntityScan(basePackages = "com.edgedo.model.entity")
@EnableMongoRepositories(basePackages = "com.edgedo.*")
@EnableMongoAuditing
public class MongodbConfig {

    @Autowired
    private MongoConfig mongoConfig;

    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoMappingContext context) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mappingConverter;
    }

    @Bean
    public MongoDbFactory mongoDbFactory() {
        String uri = DatabaseUtils.connectToReplicaMongodb(
                this.mongoConfig.getHosts(),
                this.mongoConfig.getDatabase(),
                this.mongoConfig.getUsername(),
                this.mongoConfig.getPassword(),
                this.mongoConfig.getAuthenticationDatabase());
        return new SimpleMongoDbFactory(new MongoClientURI(uri, new MongoClientOptions.Builder()));
    }
}
