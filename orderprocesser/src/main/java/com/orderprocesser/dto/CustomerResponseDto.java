package com.orderprocesser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {

    private Long orderId;

    private double orderCost;

    private int numberOfItems;

    private String orderStatus;
}
