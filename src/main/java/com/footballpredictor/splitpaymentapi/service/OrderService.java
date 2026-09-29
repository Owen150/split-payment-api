package com.footballpredictor.splitpaymentapi.service;

import com.footballpredictor.splitpaymentapi.entity.Order;
import com.footballpredictor.splitpaymentapi.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Service class for handling order-related operations
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Order not found"
                        )
                );
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setTotalAmount(order.getTotalAmount());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
    }
}