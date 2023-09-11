package com.orderprocesser.controller;

import com.orderprocesser.dto.CustomerResponseDto;
import com.orderprocesser.service.CustomerProcesserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CustomerProcesserController {

    @Autowired
    private CustomerProcesserService customerProcesserService;

    @GetMapping("/customer")
    public List<CustomerResponseDto> getByCustomerOrderDetails(@RequestParam long customerId,
                                                               @RequestParam(required = false) String phNumber,
                                                               @RequestParam(required = false) String email) {

        return customerProcesserService.getByCustomerOrderDetails(customerId,phNumber,email);
    }
}
