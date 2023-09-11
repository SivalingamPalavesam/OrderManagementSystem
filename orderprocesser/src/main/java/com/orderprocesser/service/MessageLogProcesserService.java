package com.orderprocesser.service;

import com.orderprocesser.dto.MessageLoggerDto;
import com.orderprocesser.entity.MessageLogger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface MessageLogProcesserService {
    List<MessageLogger> getByMessageDetails(UUID messageId, Long entityId);
}
