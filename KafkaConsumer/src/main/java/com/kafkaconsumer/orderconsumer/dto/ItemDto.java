package com.kafkaconsumer.orderconsumer.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class    ItemDto {
    private Long itemId;
    private String itemName;
    private int itemQty;
    private double itemCost;
}
