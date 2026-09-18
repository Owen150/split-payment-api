package com.footballpredictor.splitpaymentapi.service;
import com.footballpredictor.splitpaymentapi.dto.CheckoutResponse;
import com.footballpredictor.splitpaymentapi.entity.*;
import com.footballpredictor.splitpaymentapi.repository.OrderRepository;
import com.footballpredictor.splitpaymentapi.repository.PaymentRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class StripePaymentService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final CommissionService commissionService;

    public StripePaymentService(OrderRepository orderRepository, PaymentRepository paymentRepository, CommissionService commissionService) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.commissionService = commissionService;
    }

    public CheckoutResponse createCheckoutSession(
            Long orderId
    ) throws StripeException {

        Order order =
                orderRepository.findById(orderId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Order not found"
                                )
                        );

        // 1. Calculate amount on backend
        BigDecimal total =
                order.getTotalAmount();

        // 2. Calculate platform fee
        BigDecimal platformFee =
                commissionService.calculate(total);

        // 3. Get seller
        Seller seller =
                order.getItems()
                        .get(0)
                        .getProduct()
                        .getSeller();

        // 4. Validate Stripe seller
        if (seller.getStripeAccountId() == null) {
            throw new RuntimeException(
                    "Seller has not completed Stripe onboarding"
            );
        }

        // 5. Convert to the smallest currency unit
        long stripeAmount =
                total
                        .multiply(
                                BigDecimal.valueOf(100)
                        )
                        .longValueExact();

        long stripeFee =
                platformFee
                        .multiply(
                                BigDecimal.valueOf(100)
                        )
                        .longValueExact();

        // 6. Build line items
        List<SessionCreateParams.LineItem>
                lineItems = new ArrayList<>();

        for (OrderItem item :
                order.getItems()) {

            long unitAmount =
                    item.getUnitPrice()
                            .multiply(
                                    BigDecimal.valueOf(100)
                            )
                            .longValueExact();

            SessionCreateParams.LineItem
                    lineItem =
                    SessionCreateParams.LineItem
                            .builder()

                            .setQuantity(
                                    item.getQuantity()
                                            .longValue()
                            )

                            .setPriceData(
                                    SessionCreateParams
                                            .LineItem
                                            .PriceData
                                            .builder()

                                            .setCurrency(
                                                    "kes"
                                            )

                                            .setUnitAmount(
                                                    unitAmount
                                            )

                                            .setProductData(
                                                    SessionCreateParams
                                                            .LineItem
                                                            .PriceData
                                                            .ProductData
                                                            .builder()
                                                            .setName(
                                                                    item
                                                                            .getProduct()
                                                                            .getName()
                                                            )
                                                            .build()
                                            )

                                            .build()
                            )

                            .build();

            lineItems.add(lineItem);
        }

        // 7. Create Checkout Session
        SessionCreateParams params =
                SessionCreateParams.builder()

                        .setMode(
                                SessionCreateParams.Mode.PAYMENT
                        )

                        .addAllLineItem(lineItems)

                        .setSuccessUrl(
                                "http://localhost:4200/payment/success"
                                        + "?session_id={CHECKOUT_SESSION_ID}"
                        )

                        .setCancelUrl(
                                "http://localhost:4200/payment/cancel"
                        )

                        .setPaymentIntentData(
                                SessionCreateParams
                                        .PaymentIntentData
                                        .builder()

                                        .setApplicationFeeAmount(
                                                stripeFee
                                        )

                                        .setTransferData(
                                                SessionCreateParams
                                                        .PaymentIntentData
                                                        .TransferData
                                                        .builder()
                                                        .setDestination(
                                                                seller
                                                                        .getStripeAccountId()
                                                        )
                                                        .build()
                                        )

                                        .build()
                        )

                        .putMetadata(
                                "order_id",
                                order.getId().toString()
                        )

                        .build();

        Session session =
                Session.create(params);

        // 8. Save payment
        Payment payment = new Payment();

        payment.setOrder(order);
        payment.setStripeCheckoutSessionId(
                session.getId()
        );
        payment.setAmount(total);
        payment.setPlatformFee(platformFee);
        payment.setSellerAmount(
                total.subtract(platformFee)
        );
        payment.setCurrency("KES");
        payment.setStatus(
                PaymentStatus.PENDING
        );
        payment.setCreatedAt(
                Instant.now()
        );

        paymentRepository.save(payment);

        return new CheckoutResponse(
                session.getId(),
                session.getUrl()
        );
    }

}
