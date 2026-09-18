package com.footballpredictor.splitpaymentapi.dto;

public class CheckoutResponse {
    // This class represents the response returned after creating a checkout session.
    private final String sessionId;
    private final String checkoutUrl;

    public CheckoutResponse(
            String sessionId,
            String checkoutUrl
    ) {
        this.sessionId = sessionId;
        this.checkoutUrl = checkoutUrl;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getCheckoutUrl() {
        return checkoutUrl;
    }
}
