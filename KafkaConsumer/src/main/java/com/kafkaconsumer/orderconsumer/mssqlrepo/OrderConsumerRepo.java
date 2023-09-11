package com.kafkaconsumer.orderconsumer.mssqlrepo;

import com.kafkaconsumer.orderconsumer.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderConsumerRepo extends JpaRepository<Order, Long> {
}
