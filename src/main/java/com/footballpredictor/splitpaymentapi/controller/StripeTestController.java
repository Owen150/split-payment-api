package com.footballpredictor.splitpaymentapi.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stripe")
public class StripeTestController {

    @GetMapping("/test")
    public String testStripe() throws StripeException {

        Account account = Account.retrieve("acct_1UFeYAPBWLn6DfMM");

        return account.getId();
    }
}
