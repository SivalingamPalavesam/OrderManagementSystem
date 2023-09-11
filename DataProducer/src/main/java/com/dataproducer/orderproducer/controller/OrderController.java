package com.dataproducer.orderproducer.controller;

import com.dataproducer.orderproducer.service.OrderProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Value("${spring.kafka.order.topic.name}")
    private String orderTopic;

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping(path = "send-message")
    public void sendMessageToKafka(@RequestBody String request) {
        orderProducer.sendMessage(request, orderTopic);
    }
}



