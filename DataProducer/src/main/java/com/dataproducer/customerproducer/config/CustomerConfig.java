package com.dataproducer.customerproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class CustomerConfig {

    @Value("${spring.kafka.customer.topic.name}")
    private String customerTopic;
    @Bean
    public NewTopic customertopic(){
        return TopicBuilder.name(customerTopic)
                .build();
    }
}
