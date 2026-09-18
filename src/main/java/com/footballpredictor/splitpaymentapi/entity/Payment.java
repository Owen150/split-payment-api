package com.footballpredictor.splitpaymentapi.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(unique = true)
    private String stripeCheckoutSessionId;

    @Column(unique = true)
    private String stripePaymentIntentId;

    private BigDecimal amount;

    private BigDecimal platformFee;

    private BigDecimal sellerAmount;

    private String currency;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private Instant createdAt;

    // Getters and Setters - lombok will generate them automatically
}
