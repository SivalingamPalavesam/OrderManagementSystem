package com.orderprocesser.controller;

import com.orderprocesser.dto.CustomerResponseDto;
import com.orderprocesser.dto.ItemResponseDto;
import com.orderprocesser.service.CustomerProcesserService;
import com.orderprocesser.service.ItemProcesserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ItemProcesserController {

    @Autowired
    private ItemProcesserService itemProcesserService;

    @GetMapping("/itemDetails")
    public ItemResponseDto getByItemDetails(@RequestParam("itemId") long id) {
        return itemProcesserService.getByItemDetails(id);
    }


}
