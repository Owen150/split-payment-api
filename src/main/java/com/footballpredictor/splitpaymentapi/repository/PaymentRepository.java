package com.footballpredictor.splitpaymentapi.repository;
import com.footballpredictor.splitpaymentapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.hibernate.internal.util.Optional;
import java.util.Optional;

// The JpaRepository interface provides CRUD operations for the Payment entity, with Long as the type of the primary key.
@Repository
public interface PaymentRepository
    extends JpaRepository<Payment, Long> {
    Optional<Payment>
    findByStripeCheckoutSessionId(
            String sessionId
    );
}
