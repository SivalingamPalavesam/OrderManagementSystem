package com.orderprocesser.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


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
