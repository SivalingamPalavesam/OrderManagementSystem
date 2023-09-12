package com.kafkaconsumer.orderconsumer.cassandrarepo;

import com.kafkaconsumer.orderconsumer.entity.MessageLogger;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageLogRepository extends CassandraRepository<MessageLogger , Long> {
}
