package com.dataproducer.itemproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ItemConfig {

    @Value("${spring.kafka.item.topic.name}")
    private String itemTopic;
    @Bean
    public NewTopic itemTopic(){
        return TopicBuilder.name(itemTopic)
                .build();
    }
}
