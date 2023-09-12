package com.orderprocesser.repository;

import com.orderprocesser.entity.MessageLogger;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface MessageLogRepository extends CassandraRepository<MessageLogger, UUID> {

    List<MessageLogger> findByMsgid(UUID messageId);

}
