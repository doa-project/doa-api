package org.example.doaapiproject.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "org.example.doaapiproject.repositories")
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb+srv://doa_user:0GcIgJHwIz3sl7su@dbdoa.jm8dctp.mongodb.net/?retryWrites=true&w=majority&appName=dbdoa");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "dbdoa");
    }
}
