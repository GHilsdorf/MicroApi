package com.training.microapi.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author rfaita
 */
@Configuration
@EnableMongoRepositories("com.training.microapi.server.repository")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${api.mongodb.hostname}")
    private String mongoHost;

    @Value("${api.mongodb.port}")
    private String mongoPort;

    @Value("${api.mongodb.database}")
    private String mongoDB;

    @Override
    @Bean
    public MongoClient mongoClient() {

        return new MongoClient(mongoHost + ":" + mongoPort);
    }

    @Override
    protected String getDatabaseName() {

        return mongoDB;
    }

}
