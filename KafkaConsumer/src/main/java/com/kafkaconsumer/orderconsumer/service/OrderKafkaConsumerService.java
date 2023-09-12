package com.kafkaconsumer.orderconsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kafkaconsumer.orderconsumer.cassandrarepo.MessageLogRepository;
import com.kafkaconsumer.orderconsumer.cassandrarepo.OrderDetailsRepository;
import com.kafkaconsumer.orderconsumer.dto.MessageLoggerDto;
import com.kafkaconsumer.orderconsumer.dto.OrderDto;
import com.kafkaconsumer.orderconsumer.entity.Customer;
import com.kafkaconsumer.orderconsumer.entity.MessageLogger;
import com.kafkaconsumer.orderconsumer.entity.Order;
import com.kafkaconsumer.orderconsumer.entity.OrderDetail;
import com.kafkaconsumer.orderconsumer.mapper.OrderMapper;
import com.kafkaconsumer.orderconsumer.mssqlrepo.CustomerConsumerRepo;
import com.kafkaconsumer.orderconsumer.mssqlrepo.OrderConsumerRepo;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderKafkaConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(OrderKafkaConsumerService.class);

    @Autowired
    private OrderConsumerRepo orderRepo;

    @Autowired
    private OrderDetailsRepository orderDetailsRepo;

    @Autowired
    private CustomerConsumerRepo customerRepo;

    @Autowired
    private MessageLogRepository messageLogRepository;

    @KafkaListener(topics = "orders_topic", groupId = "orderconsumergroup")
    public void consume(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            OrderDto orderDto = objectMapper.readValue(message, OrderDto.class);
            Order order = OrderMapper.INSTANCE.jsonToOrderEntity(orderDto);

            Order orders = orderRepo.save(order);
            Customer customer = OrderMapper.INSTANCE.jsonToCustomerEntity(orderDto.getCustomer());
            Customer saved = customerRepo.save(customer);
            orderDto.setOrderId(orders.getOrderId());
            orderDto.getCustomer().setCustId(saved.getCustId());

            orderDto.getItems().stream().map(itemDto -> {
                OrderDetail orderDetail = OrderMapper.INSTANCE.jsonToOrderDetailsEntity(orderDto, itemDto);
                orderDetail.setId(UUID.randomUUID());
                return orderDetailsRepo.save(orderDetail);
            }).toList();


            MessageLogger messageLog = new MessageLogger();
            messageLog.setMsgid(UUID.randomUUID());
            messageLog.setEntityId(orders.getOrderId());
            messageLog.setMsgcreateTs(LocalDateTime.now());
            messageLog.setMsgString(message);
           messageLogRepository.save(messageLog);

            logger.info(" Order Values stored");
        } catch (IOException e) {
            logger.error("Error parsing JSON message: " + e.getMessage());
        }
    }
}


