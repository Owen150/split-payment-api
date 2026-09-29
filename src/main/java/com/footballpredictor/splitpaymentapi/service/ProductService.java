package com.footballpredictor.splitpaymentapi.service;

import com.footballpredictor.splitpaymentapi.entity.Product;
import com.footballpredictor.splitpaymentapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with id: " + id
                        )
                );
    }

    // CREATE Product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // UPDATE
    public Product updateProduct(
            Long id,
            Product productDetails
    ) {

        Product product = productRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with id: " + id
                        )
                );

        product.setName(productDetails.getName());

        product.setDescription(
                productDetails.getDescription()
        );
        product.setPrice(
                productDetails.getPrice()
        );
        product.setStock(
                productDetails.getStock()
        );
        product.setImageUrl(
                productDetails.getImageUrl()
        );
        product.setQuantity(
                productDetails.getQuantity()
        );
        product.setSeller(
                productDetails.getSeller()
        );
        return productRepository.save(product);
    }

    // DELETE
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException(
                    "Product not found with id: " + id
            );
        }
        productRepository.deleteById(id);
    }
}
