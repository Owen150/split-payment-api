package com.footballpredictor.splitpaymentapi.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "sellers")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String businessName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "stripe_account_id", unique = true)
    private String stripeAccountId;

    @Column(nullable = false)
    private boolean stripeOnboardingComplete;
}
