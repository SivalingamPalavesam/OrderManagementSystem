package com.kafkaconsumer.orderconsumer.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "orderdetail")
@Getter
@Setter
public class OrderDetail {
    @PrimaryKey
    private UUID id;

    private Long orderDetailsId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime orderCreateTs;

    private Long orderId;

    private Long itemId;

    private String itemName;

    private int itemQty;

    private Long custId;
}