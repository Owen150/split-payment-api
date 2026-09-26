package com.footballpredictor.splitpaymentapi.controller;

import com.footballpredictor.splitpaymentapi.entity.Product;
import com.footballpredictor.splitpaymentapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }

    // GET ONE
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                productService.getProductById(id)
        );
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody Product product
    ) {
        Product createdProduct =
                productService.createProduct(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdProduct);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product
    ) {
        Product updatedProduct =
                productService.updateProduct(
                        id,
                        product
                );
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
