package com.footballpredictor.splitpaymentapi.service;

import com.footballpredictor.splitpaymentapi.entity.Order;
import com.footballpredictor.splitpaymentapi.entity.OrderStatus;
import com.footballpredictor.splitpaymentapi.entity.Payment;
import com.footballpredictor.splitpaymentapi.entity.PaymentStatus;
import com.footballpredictor.splitpaymentapi.repository.OrderRepository;
import com.footballpredictor.splitpaymentapi.repository.PaymentRepository;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            OrderRepository orderRepository
    ) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    public void processEvent(Event event) {
        if ("checkout.session.completed".equals(event.getType())) {
            handleCheckoutCompleted(event);
        }
    }

    private void handleCheckoutCompleted(Event event) {
        Session session =
                (Session) event
                        .getDataObjectDeserializer()
                        .getObject()
                        .orElseThrow();

        Payment payment =
                paymentRepository
                        .findByStripeCheckoutSessionId(session.getId())
                        .orElseThrow();

        // Idempotency
        if (payment.getStatus() == PaymentStatus.SUCCEEDED) {
            return;
        }

        payment.setStatus(PaymentStatus.SUCCEEDED);

        payment.setStripePaymentIntentId(session.getPaymentIntent());

        paymentRepository.save(payment);

        Order order = payment.getOrder();

        order.setStatus(OrderStatus.PAID);

        orderRepository.save(order);
    }
}
