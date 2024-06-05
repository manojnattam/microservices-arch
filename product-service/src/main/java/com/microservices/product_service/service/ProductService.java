package com.microservices.product_service.service;

import com.microservices.product_service.dto.ProductRequest;
import com.microservices.product_service.dto.ProductResponse;
import com.microservices.product_service.modal.Product;
import com.microservices.product_service.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){

        try {
            Product product = Product.builder().name(productRequest.getName())
                    .description(productRequest.getDescription())
//                    .price(productRequest.getPrice())
                    .build();
            productRepository.save(product);
            log.info("Product {} is saved", product.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<ProductResponse> getProducts() {
        List<Product> products= productRepository.findAll();

        return products.stream().map(product ->
                new ProductResponse(product.getId(),product.getName(),product.getDescription()))
                .collect(Collectors.toList());


    }
}
