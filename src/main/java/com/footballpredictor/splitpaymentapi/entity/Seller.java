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

    private String businessName;

    @Column(unique = true)
    private String email;

    @Column(name = "stripe_account_id")
    private String stripeAccountId;

    private boolean stripeOnboardingComplete;

    // Getters and Setters - lombok will generate them automatically
}
