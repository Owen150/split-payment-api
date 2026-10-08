package com.footballpredictor.splitpaymentapi.service;

import com.footballpredictor.splitpaymentapi.dto.CreateProductRequest;
import com.footballpredictor.splitpaymentapi.entity.Category;
import com.footballpredictor.splitpaymentapi.entity.Product;
import com.footballpredictor.splitpaymentapi.entity.Seller;
import com.footballpredictor.splitpaymentapi.repository.CategoryRepository;
import com.footballpredictor.splitpaymentapi.repository.ProductRepository;
import com.footballpredictor.splitpaymentapi.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Service class for handling product-related operations
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.categoryRepository = categoryRepository;
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
//    public Product createProduct(Product product) {
//        return productRepository.save(product);
//    }

    public Product createProduct(
            CreateProductRequest request
    ) {

        // Find seller
        Seller seller = sellerRepository
                .findById(request.getSellerId())
                .orElseThrow(
                        () -> new RuntimeException(
                                "Seller not found with ID: "
                                        + request.getSellerId()
                        )
                );


        // Find category
        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(
                        () -> new RuntimeException(
                                "Category not found with ID: "
                                        + request.getCategoryId()
                        )
                );


        // Create Product
        Product product = new Product();

        product.setName(request.getName());

        product.setDescription(
                request.getDescription()
        );

        product.setPrice(
                request.getPrice()
        );

        product.setQuantity(
                request.getQuantity()
        );

        product.setImageUrl(
                request.getImageUrl()
        );

        product.setStock(
                request.getStock()
        );

        product.setInStock(
                request.getInStock()
        );

        product.setRating(
                request.getRating()
        );

        product.setReviewCount(
                request.getReviewCount()
        );

        // THIS IS THE IMPORTANT PART
        product.setSeller(seller);

        // THIS IS THE IMPORTANT PART
        product.setCategory(category);


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
