package com.kafkaconsumer.orderconsumer.mapper;

import com.kafkaconsumer.orderconsumer.dto.CustomerDto;
import com.kafkaconsumer.orderconsumer.dto.ItemDto;
import com.kafkaconsumer.orderconsumer.dto.OrderDto;
import com.kafkaconsumer.orderconsumer.entity.Customer;
import com.kafkaconsumer.orderconsumer.entity.Item;
import com.kafkaconsumer.orderconsumer.entity.Order;
import com.kafkaconsumer.orderconsumer.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "orderCreateTs", target = "orderCreateTs")
    @Mapping(source = "orderStatus", target = "orderStatus")
    @Mapping(source = "orderCost", target = "orderCost")
    Order jsonToOrderEntity(OrderDto orderDto);

    @Mapping(source = "customerDto.custId", target = "custId")
    @Mapping(source = "customerDto.firstname", target = "firstname")
    @Mapping(source = "customerDto.lastname", target = "lastname")
    @Mapping(source = "customerDto.phno", target = "phno")
    @Mapping(source = "customerDto.email", target = "email")
    Customer jsonToCustomerEntity(CustomerDto customerDto);

    @Mapping(source = "itemId", target = "itemId")
    @Mapping(source = "itemName", target = "itemName")
    @Mapping(source = "itemQty", target = "itemQty")
    @Mapping(source = "itemCost", target = "itemCost")
    Item jsonToItemEntity(ItemDto itemDto);

    @Mapping(source = "orderDto.orderDetailsId", target = "orderDetailsId")
    @Mapping(source = "orderDto.orderCreateTs", target = "orderCreateTs")
    @Mapping(source = "orderDto.orderId", target = "orderId")
    @Mapping(source = "itemDto.itemId", target = "itemId")
    @Mapping(source = "itemDto.itemName", target = "itemName")
    @Mapping(source = "itemDto.itemQty", target = "itemQty")
    @Mapping(source = "orderDto.customer.custId", target = "custId")
    OrderDetail jsonToOrderDetailsEntity(OrderDto orderDto,ItemDto itemDto);
}