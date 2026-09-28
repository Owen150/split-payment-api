package com.footballpredictor.splitpaymentapi.controller;
import com.footballpredictor.splitpaymentapi.dto.SellerOnboardingResponse;
import com.footballpredictor.splitpaymentapi.entity.Product;
import com.footballpredictor.splitpaymentapi.entity.Seller;
import com.footballpredictor.splitpaymentapi.service.SellerService;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers() {
        return ResponseEntity.ok(
                sellerService.getAllSellers()
        );
    }
}
