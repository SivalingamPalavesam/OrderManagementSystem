package com.kafkaconsumer.itemconsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kafkaconsumer.orderconsumer.cassandrarepo.ItemConsumerRepo;
import com.kafkaconsumer.orderconsumer.cassandrarepo.MessageLogRepository;
import com.kafkaconsumer.orderconsumer.dto.ItemDto;
import com.kafkaconsumer.orderconsumer.entity.Item;
import com.kafkaconsumer.orderconsumer.entity.MessageLogger;
import com.kafkaconsumer.orderconsumer.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemKafkaConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(ItemKafkaConsumerService.class);


    private final ItemConsumerRepo itemConsumerRepo;
    @Autowired
    private MessageLogRepository messageLogRepository;
    @KafkaListener(topics = "item_topic", groupId = "itemconsumergroup")
    public void consume(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            ItemDto itemDto = objectMapper.readValue(message, ItemDto.class);
            Item itemEntity = OrderMapper.INSTANCE.jsonToItemEntity(itemDto);
            itemConsumerRepo.save(itemEntity);
            logger.info("item values saved");

            MessageLogger messageLog = new MessageLogger();
            messageLog.setMsgid(UUID.randomUUID());
            messageLog.setEntityId(itemEntity.getItemId());
            messageLog.setMsgcreateTs(LocalDateTime.now());
            messageLog.setMsgString(message);
            messageLogRepository.save(messageLog);

        } catch (IOException e) {
            logger.error("Error parsing JSON message: " + e.getMessage());
        }
    }
}
