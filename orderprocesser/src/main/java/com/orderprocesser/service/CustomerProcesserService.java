package com.orderprocesser.service;

import com.orderprocesser.dto.CustomerResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerProcesserService {
    List<CustomerResponseDto> getByCustomerOrderDetails(long id, String phNumber ,String email);
}
