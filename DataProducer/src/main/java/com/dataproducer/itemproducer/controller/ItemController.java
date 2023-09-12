package com.dataproducer.itemproducer.controller;

import com.dataproducer.itemproducer.service.ItemProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Value("${spring.kafka.item.topic.name}")
    private String itemTopic;

    private final ItemProducer itemProducer;

    public ItemController(ItemProducer itemProducer) {
        this.itemProducer = itemProducer;
    }

    @PostMapping(path = "/itemMessage")
    public void sendMessageToKafka(@RequestBody String itemMessage ) {
        itemProducer.sendMessage(itemMessage,itemTopic);
    }
}
