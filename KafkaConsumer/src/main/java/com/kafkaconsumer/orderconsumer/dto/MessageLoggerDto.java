package com.kafkaconsumer.orderconsumer.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@UserDefinedType
public class MessageLoggerDto {

        private UUID msgid;

        private long entityId;

        private LocalDateTime msgcreateTs;

        @CassandraType(type = CassandraType.Name.TEXT)
        private String msgString;

}
