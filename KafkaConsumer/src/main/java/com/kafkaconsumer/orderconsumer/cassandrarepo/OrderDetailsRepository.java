package com.kafkaconsumer.orderconsumer.cassandrarepo;

import com.kafkaconsumer.orderconsumer.entity.OrderDetail;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface OrderDetailsRepository extends CassandraRepository<OrderDetail, Long> {
}
