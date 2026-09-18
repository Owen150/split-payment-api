package com.footballpredictor.splitpaymentapi.dto;

public class CreateCheckoutRequest {
    // This class represents a request to create a checkout session for a specific order.
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
