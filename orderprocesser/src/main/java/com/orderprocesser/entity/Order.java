package com.orderprocesser.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "order_tables")
public class Order {

    @Id
    private Long orderid;

    @Column(name = "order_create_ts")
    private LocalDateTime orderCreateTs;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_cost")
    private double orderCost;
}

