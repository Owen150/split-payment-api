package com.footballpredictor.splitpaymentapi.repository;

import com.footballpredictor.splitpaymentapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository
        extends JpaRepository<Category, Long> {
}
