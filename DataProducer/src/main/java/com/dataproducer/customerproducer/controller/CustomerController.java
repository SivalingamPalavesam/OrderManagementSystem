package com.dataproducer.customerproducer.controller;

import com.dataproducer.customerproducer.service.CustomerProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Value("${spring.kafka.customer.topic.name}")
    private String customerTopic;

    private final CustomerProducer customerProducer;

    public CustomerController(CustomerProducer customerProducer) {
        this.customerProducer = customerProducer;
    }

    @PostMapping(path = "/customerMessage")
    public void sendMessageToKafka(@RequestBody String itemMessage ) {
        customerProducer.sendMessage(itemMessage,customerTopic);
    }
}
