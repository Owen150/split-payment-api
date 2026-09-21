package com.footballpredictor.splitpaymentapi.config;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

// That is the mechanism that allows the Stripe Java SDK to authenticate requests.
// You therefore do not need to create a WebClient just to use the Stripe Java SDK.
@Configuration
public class StripeConfig {

    @Value("${stripe.secret-key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }
}
