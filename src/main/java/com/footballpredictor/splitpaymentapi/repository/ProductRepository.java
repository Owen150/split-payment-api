package com.footballpredictor.splitpaymentapi.repository;

import com.footballpredictor.splitpaymentapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {
}
