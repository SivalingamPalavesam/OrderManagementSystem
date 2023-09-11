package com.orderprocesser.service.Impl;

import com.orderprocesser.dto.MessageLoggerDto;
import com.orderprocesser.entity.MessageLogger;
import com.orderprocesser.repository.MessageLogRepository;
import com.orderprocesser.service.MessageLogProcesserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class MessageLogProcesserServiceImpl implements MessageLogProcesserService {

    @Autowired
    private MessageLogRepository messageLogRepository;

    @Override
    public List<MessageLogger>  getByMessageDetails(UUID messageId, Long entityId) {

        List<MessageLogger> messagesByMsgid = messageLogRepository.findByMsgid(messageId);

        List<MessageLogger> filteredMessages = messagesByMsgid
                .stream()
                .filter(message -> entityId.equals(message.getEntityId()))
                .collect(Collectors.toList());

        return filteredMessages;
    }
}
