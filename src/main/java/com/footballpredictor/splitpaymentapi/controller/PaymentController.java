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

    private final StripePaymentService paymentService;

    public PaymentController(StripePaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/checkout")
    public CheckoutResponse checkout(
            @RequestBody CreateCheckoutRequest request
    ) throws StripeException {

        return paymentService
                .createCheckoutSession(
                        request.getOrderId()
                );
    }
}
