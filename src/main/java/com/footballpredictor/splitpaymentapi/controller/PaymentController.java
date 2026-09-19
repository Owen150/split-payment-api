package com.footballpredictor.splitpaymentapi.controller;
import com.footballpredictor.splitpaymentapi.dto.CheckoutResponse;
import com.footballpredictor.splitpaymentapi.dto.CreateCheckoutRequest;
import com.footballpredictor.splitpaymentapi.service.StripePaymentService;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final StripePaymentService stripePaymentService;

    public PaymentController(StripePaymentService stripePaymentService) {
        this.stripePaymentService = stripePaymentService;
    }

    @PostMapping("/checkout")
    public CheckoutResponse createCheckout(
            @RequestBody CreateCheckoutRequest request
    ) throws StripeException {

        return stripePaymentService
                .createCheckoutSession(
                        request.getOrderId()
                );
    }
}
