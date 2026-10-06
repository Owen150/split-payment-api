package com.footballpredictor.splitpaymentapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

//    @Column(nullable = false)
    private Integer rating;

//    @Column(nullable = false)
    private Integer reviewCount;

    @Column(nullable = false)
    private String imageUrl;

    // Check usage - Available/Remaining Stock
//    @Column(nullable = false)
    private Integer stock;

    private Boolean inStock;

    // The selected Product/OrderItem quantity in every prospective order.
    // The selected quantity of an OrderItem inside an Order in short, which, in other words, represents the total number/quantity of a selected Product inside an Order.
    // If the Order status is FULFILLED, subtract the sold Product/OrderItem quantity/quantities from the specific Products' stock. Update the remaining stock balance which is used to update the boolean value of the column/variable inStock.
    @Column(nullable = false)
    private Integer quantity;

    // Set the default value to be false. If the value of stock < or == 0, the value of inStock = false. If the value of stock is > 0, the value of inStock = true
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {}

    public Product(
            Long id,
            String name,
            String description,
            BigDecimal price,
            Integer quantity,
            String imageUrl,
            Seller seller,
            Integer stock,
            Category category,
            Boolean inStock,
            Integer rating,
            Integer reviewCount
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.seller = seller;
        this.stock = stock;
        this.category = category;
        this.inStock = inStock;
        this.rating = rating;
        this.reviewCount = reviewCount;
    }
}
