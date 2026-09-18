package com.footballpredictor.splitpaymentapi.service;
import com.footballpredictor.splitpaymentapi.entity.Seller;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.param.AccountCreateParams;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    public String createStripeAccount(
            Seller seller
    ) throws StripeException {

        AccountCreateParams params =
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
                Account.create(params);

        seller.setStripeAccountId(
                account.getId()
        );

        return account.getId();
    }
}
