package com.orderprocesser.repository;

import com.orderprocesser.entity.Item;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemProcesserRepository extends CassandraRepository<Item, Long> {

    Item getAllValuesByitemId(long id);

    List<Item> findAllBy();
}
