package com.orderprocesser.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class MessageLoggerDto {

        @CassandraType(type = CassandraType.Name.TEXT)
        private String msgString;

}
