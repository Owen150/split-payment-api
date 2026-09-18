package com.footballpredictor.splitpaymentapi.controller;
import com.footballpredictor.splitpaymentapi.dto.SellerOnboardingResponse;
import com.footballpredictor.splitpaymentapi.service.SellerService;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/{id}/stripe/onboard")
    public SellerOnboardingResponse onboard(
            @PathVariable Long id
    ) throws StripeException {

        return sellerService
                .createOnboardingLink(id);
    }
}
