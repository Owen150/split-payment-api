package com.footballpredictor.splitpaymentapi.service;

import com.footballpredictor.splitpaymentapi.dto.SellerOnboardingResponse;
import com.footballpredictor.splitpaymentapi.entity.Seller;
import com.footballpredictor.splitpaymentapi.repository.SellerRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountLinkCreateParams;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService(
            SellerRepository sellerRepository
    ) {
        this.sellerRepository = sellerRepository;
    }

    public SellerOnboardingResponse createOnboardingLink(
            Long sellerId
    ) throws StripeException {

        // 1. Find seller in PostgreSQL
        Seller seller =
                sellerRepository
                        .findById(sellerId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Seller not found"
                                )
                        );

        // 2. Create Stripe Express account
        if (seller.getStripeAccountId() == null) {

            AccountCreateParams accountParams =
                    AccountCreateParams.builder()

                            .setType(
                                    AccountCreateParams.Type.EXPRESS
                            )

                            .setCountry("KE")

                            .setEmail(
                                    seller.getEmail()
                            )

                            .build();

            Account account =
                    Account.create(accountParams);

            // 3. Save Stripe account ID
            seller.setStripeAccountId(
                    account.getId()
            );

            seller.setStripeOnboardingComplete(
                    false
            );

            sellerRepository.save(seller);
        }

        // 4. Create Stripe onboarding link
        AccountLinkCreateParams linkParams =
                AccountLinkCreateParams.builder()

                        .setAccount(
                                seller.getStripeAccountId()
                        )

                        .setRefreshUrl(
                                "http://localhost:4200/seller/stripe/refresh"
                        )

                        .setReturnUrl(
                                "http://localhost:4200/seller/stripe/return"
                        )

                        .setType(
                                AccountLinkCreateParams.Type.ACCOUNT_ONBOARDING
                        )

                        .build();

        AccountLink accountLink =
                AccountLink.create(linkParams);

        // 5. Return onboarding URL
        return new SellerOnboardingResponse(
                seller.getStripeAccountId(),
                accountLink.getUrl()
        );
    }
}