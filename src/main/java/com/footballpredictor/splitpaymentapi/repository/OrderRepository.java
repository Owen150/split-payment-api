package com.footballpredictor.splitpaymentapi.repository;

import com.footballpredictor.splitpaymentapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository
    extends JpaRepository<Order, Long> {
}
