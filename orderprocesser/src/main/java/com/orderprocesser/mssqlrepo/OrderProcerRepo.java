package com.orderprocesser.mssqlrepo;

import com.orderprocesser.entity.Order;
import com.orderprocesser.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProcerRepo extends JpaRepository<Order ,Long> {


    List<Order> findByOrderid(Long orderId);
}
