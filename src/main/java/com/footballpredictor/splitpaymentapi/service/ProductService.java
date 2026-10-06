package com.footballpredictor.splitpaymentapi.service;

import com.footballpredictor.splitpaymentapi.entity.Product;
import com.footballpredictor.splitpaymentapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Service class for handling product-related operations
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET ALL Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET ONE Product
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // CREATE Product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // UPDATE Product
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        product.setImageUrl(productDetails.getImageUrl());
        product.setQuantity(productDetails.getQuantity());
        product.setSeller(productDetails.getSeller());
        return productRepository.save(product);
    }

    // DELETE Product
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
