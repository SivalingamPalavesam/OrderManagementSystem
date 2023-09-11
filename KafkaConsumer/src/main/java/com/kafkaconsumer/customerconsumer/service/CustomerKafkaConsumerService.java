package com.kafkaconsumer.customerconsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kafkaconsumer.itemconsumer.service.ItemKafkaConsumerService;
import com.kafkaconsumer.orderconsumer.cassandrarepo.MessageLogRepository;
import com.kafkaconsumer.orderconsumer.dto.CustomerDto;
import com.kafkaconsumer.orderconsumer.dto.ItemDto;
import com.kafkaconsumer.orderconsumer.entity.Customer;
import com.kafkaconsumer.orderconsumer.entity.Item;
import com.kafkaconsumer.orderconsumer.entity.MessageLogger;
import com.kafkaconsumer.orderconsumer.mapper.OrderMapper;
import com.kafkaconsumer.orderconsumer.mssqlrepo.CustomerConsumerRepo;
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
public class CustomerKafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerKafkaConsumerService.class);


    private final CustomerConsumerRepo customerConsumerRepo;

    @Autowired
    private MessageLogRepository messageLogRepository;

    @KafkaListener(topics = "customer_topic", groupId = "customerconsumergroup")
    public void consume(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            CustomerDto customerDto = objectMapper.readValue(message, CustomerDto.class);
            Customer customer = OrderMapper.INSTANCE.jsonToCustomerEntity(customerDto);
            customerConsumerRepo.save(customer);
            logger.info("customer values saved");

            MessageLogger messageLog = new MessageLogger();
            messageLog.setMsgid(UUID.randomUUID());
            messageLog.setEntityId(customer.getCustId());
            messageLog.setMsgcreateTs(LocalDateTime.now());
            messageLog.setMsgString(message);
            messageLogRepository.save(messageLog);
        } catch (IOException e) {
            logger.error("Error parsing JSON message: " + e.getMessage());
        }
    }
}
