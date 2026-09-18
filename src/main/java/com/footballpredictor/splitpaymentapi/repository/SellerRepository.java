package com.footballpredictor.splitpaymentapi.repository;
import com.footballpredictor.splitpaymentapi.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository
        extends JpaRepository<Seller, Long> {
}
