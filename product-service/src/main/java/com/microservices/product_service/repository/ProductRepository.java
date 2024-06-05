package com.microservices.product_service.repository;

import com.microservices.product_service.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
