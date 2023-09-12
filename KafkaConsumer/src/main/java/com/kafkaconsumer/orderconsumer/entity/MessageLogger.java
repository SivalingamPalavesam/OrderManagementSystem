package com.kafkaconsumer.orderconsumer.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("message_log")
@Setter
@Getter
public class MessageLogger {
    @PrimaryKey
    private UUID msgid;

    @Column("entity_id")
    private Long entityId;

    @Column("msg_create_ts")
    private LocalDateTime msgcreateTs;

    @Column("msgstring")
    private String msgString;

}
