package com.footballpredictor.splitpaymentapi.service;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CommissionService {
    private static final BigDecimal RATE =
            new BigDecimal("0.05");

    public BigDecimal calculate(
            BigDecimal orderTotal) {

        return orderTotal
                .multiply(RATE)
                .setScale(
                        2,
                        RoundingMode.HALF_UP
                );
    }
}
