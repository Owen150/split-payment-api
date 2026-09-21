package com.footballpredictor.splitpaymentapi.config;

import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    // Because we are using Angular as the frontend and Spring Boot as the backend, we need to configure CORS (Cross-Origin Resource Sharing) to allow requests from the Angular application to the Spring Boot API.
    // This configuration allows cross-origin requests from the Angular frontend running on localhost:4200 to the Spring Boot backend running on localhost:8080.
    // For production, replace the localhost origin with your actual frontend domain.
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry
                    .addMapping("/api/**")
                    .allowedOrigins("http://localhost:4200")
                    .allowedMethods(
                            "GET",
                            "POST",
                            "PUT",
                            "DELETE",
                            "OPTIONS"
                    )
                    .allowedHeaders("*");
            }
        };
    }
}
