package com.orderprocesser.service.Impl;

import com.orderprocesser.dto.ItemResponseDto;
import com.orderprocesser.entity.Item;
import com.orderprocesser.exceptionhandler.CustomerNotFoundException;
import com.orderprocesser.repository.ItemProcesserRepository;
import com.orderprocesser.service.ItemProcesserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemProcesserServiceImpl implements ItemProcesserService {

    @Autowired
    private ItemProcesserRepository itemProcesseRepository;

    @Override
    @SneakyThrows
    public ItemResponseDto getByItemDetails(long id) {
        Item item = itemProcesseRepository.getAllValuesByitemId(id);
        List<Item> items = itemProcesseRepository.findAllBy();


        if (item == null) {
            throw new CustomerNotFoundException("Item  id " + id + " not found");
        }
        String targetItemName = item.getItemName();
        long itemCount = items.stream()
                .map(Item::getItemName)
                .filter(s -> s.equals(targetItemName))
                .count();

        Map<String, Integer> itemNameTotalQuantity = items.stream()
                .filter(itemName -> itemName.getItemName().equals(targetItemName))
                .collect(Collectors.groupingBy(
                        Item::getItemName,
                        Collectors.summingInt(Item::getItemQty)
                ));

        int totalQuantity = itemNameTotalQuantity.getOrDefault(targetItemName, 0);
        ItemResponseDto responseDto = new ItemResponseDto();
        responseDto.setItemname(item.getItemName());
        responseDto.setTotalItemQty(totalQuantity);
        responseDto.setTotalOrders(itemCount);
        return responseDto;

    }
}
