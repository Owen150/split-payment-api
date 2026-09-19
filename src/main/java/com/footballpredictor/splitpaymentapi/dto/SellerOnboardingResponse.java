package com.footballpredictor.splitpaymentapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SellerOnboardingResponse {

    private String stripeAccountId;

    private String onboardingUrl;
}