package com.orderprocesser.service;

import com.orderprocesser.dto.ItemResponseDto;
import org.springframework.stereotype.Service;

@Service
public  interface ItemProcesserService  {

    ItemResponseDto getByItemDetails(long id);
}
