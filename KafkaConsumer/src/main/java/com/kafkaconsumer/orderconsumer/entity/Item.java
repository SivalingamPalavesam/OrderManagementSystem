package com.kafkaconsumer.orderconsumer.entity;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;


@Setter
@Getter
@Table
public class Item {

    @PrimaryKey
    private Long itemId;

    private String itemName;

    private int itemQty;

    private double itemCost;
}
