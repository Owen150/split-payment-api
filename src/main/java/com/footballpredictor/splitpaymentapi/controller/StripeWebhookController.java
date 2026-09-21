package com.footballpredictor.splitpaymentapi.controller;

import com.footballpredictor.splitpaymentapi.service.PaymentService;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stripe")
public class StripeWebhookController {

    @Value("${stripe.webhook-secret}")
    private String webhookSecret;

    private final PaymentService paymentService;

    public StripeWebhookController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> webhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature")
            String signature
    ) {
        try {
            Event event =
                    Webhook.constructEvent(
                            payload,
                            signature,
                            webhookSecret
                    );
            paymentService.processEvent(event);
            return ResponseEntity.ok("Webhook received");
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Webhook failed");
        }
    }
}
