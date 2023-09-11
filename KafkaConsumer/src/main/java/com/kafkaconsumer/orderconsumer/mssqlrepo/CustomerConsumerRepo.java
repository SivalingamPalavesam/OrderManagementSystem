package com.kafkaconsumer.orderconsumer.mssqlrepo;

import com.kafkaconsumer.orderconsumer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerConsumerRepo extends JpaRepository<Customer, Long> {
}
