package com.footballpredictor.splitpaymentapi.entity;

import jakarta.persistence.*;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStripeAccountId() {
        return stripeAccountId;
    }

    public void setStripeAccountId(String stripeAccountId) {
        this.stripeAccountId = stripeAccountId;
    }

    public boolean isStripeOnboardingComplete() {
        return stripeOnboardingComplete;
    }

    public void setStripeOnboardingComplete(boolean stripeOnboardingComplete) {
        this.stripeOnboardingComplete = stripeOnboardingComplete;
    }
}
