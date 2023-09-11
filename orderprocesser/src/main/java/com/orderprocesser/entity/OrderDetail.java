package com.orderprocesser.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.time.LocalDateTime;

@Table(name = "orderdetail")
@Getter
@Setter
public class OrderDetail {
    @PrimaryKey
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