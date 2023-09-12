package com.orderprocesser.service.Impl;

import com.orderprocesser.dto.CustomerResponseDto;
import com.orderprocesser.entity.Order;
import com.orderprocesser.entity.OrderDetail;
import com.orderprocesser.exceptionhandler.CustomerNotFoundException;
import com.orderprocesser.mssqlrepo.OrderProcerRepo;
import com.orderprocesser.repository.OrderDetailsProcesserRepo;
import com.orderprocesser.service.CustomerProcesserService;
import lombok.SneakyThrows;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerProcesserServiceImpl implements CustomerProcesserService {

    @Autowired
    private OrderProcerRepo orderRepo;

    @Autowired
    private OrderDetailsProcesserRepo orderDetailsRepo;

    @Override
    @SneakyThrows
    public List<CustomerResponseDto> getByCustomerOrderDetails(long id ,String phNo , String email) {
        List<OrderDetail> orderDetails = orderDetailsRepo.findAllByCustId(id);
        List<CustomerResponseDto> responseDtos = new ArrayList<>();

        if (orderDetails.isEmpty()) {
            throw new CustomerNotFoundException("Customer id " + id + " not found");
        }
        for (OrderDetail orderDetail : orderDetails) {
            Long orderId = orderDetail.getOrderId();

            List<Order> orders = orderRepo.findByOrderid(orderId);

            for (Order order : orders) {
                CustomerResponseDto responseDto = new CustomerResponseDto();
                responseDto.setOrderId(orderId);
                responseDto.setNumberOfItems(orderDetail.getItemQty());
                responseDto.setOrderCost(order.getOrderCost());
                responseDto.setOrderStatus(order.getOrderStatus());
                responseDtos.add(responseDto);
            }
        }

        return responseDtos;
    }

}
