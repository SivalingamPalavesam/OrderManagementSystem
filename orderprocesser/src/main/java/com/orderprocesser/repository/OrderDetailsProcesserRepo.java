package com.orderprocesser.repository;

import com.orderprocesser.entity.OrderDetail;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsProcesserRepo extends CassandraRepository<OrderDetail , Long> {

    @AllowFiltering
    List<OrderDetail> findAllByCustId(Long custId);
}
