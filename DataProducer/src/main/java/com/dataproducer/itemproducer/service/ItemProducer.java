package com.dataproducer.itemproducer.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class ItemProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ItemProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String values, String topicName) {
        System.out.println(values);
        kafkaTemplate.send(topicName, values);
    }
}
