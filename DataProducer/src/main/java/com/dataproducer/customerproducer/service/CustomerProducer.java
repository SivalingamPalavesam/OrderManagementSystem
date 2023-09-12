package com.dataproducer.customerproducer.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class CustomerProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CustomerProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String values, String topicName) {
        kafkaTemplate.send(topicName, values);
    }
}
