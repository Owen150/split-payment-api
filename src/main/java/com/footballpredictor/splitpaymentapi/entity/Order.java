package com.footballpredictor.splitpaymentapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private String currency = "KES";

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {}

    public Order(
            Long id,
            BigDecimal totalAmount,
            String currency,
            OrderStatus status,
            List<OrderItem> items
    ) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.currency = currency;
        this.status = status;
        this.items = items;
    }
}
