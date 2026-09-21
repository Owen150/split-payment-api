package com.footballpredictor.splitpaymentapi.service;

import com.footballpredictor.splitpaymentapi.entity.Order;
import com.footballpredictor.splitpaymentapi.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    // Service class for handling order-related operations
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrder(Long id) {
        return orderRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Order not found"
                        )
                );
    }
}