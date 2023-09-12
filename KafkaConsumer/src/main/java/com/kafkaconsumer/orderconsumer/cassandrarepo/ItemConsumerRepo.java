package com.kafkaconsumer.orderconsumer.cassandrarepo;

import com.kafkaconsumer.orderconsumer.entity.Item;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ItemConsumerRepo extends CassandraRepository<Item, Long> {
}
