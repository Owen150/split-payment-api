package com.footballpredictor.splitpaymentapi.repository;
import com.footballpredictor.splitpaymentapi.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Interfaces in Java are used to define contracts for classes to implement. In this case, the SellerRepository interface extends JpaRepository, which means it inherits methods for performing CRUD operations on Seller entities, with Long as the type of the primary key. The @Repository annotation indicates that this interface is a Spring Data repository, allowing Spring to automatically implement the necessary methods at runtime.
@Repository
public interface SellerRepository
        extends JpaRepository<Seller, Long> {
}
