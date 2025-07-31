package com.shanuka.microservice.controller;

import com.shanuka.microservice.dto.request.ProductRequest;
import com.shanuka.microservice.dto.request.ProductUpdateRequest;
import com.shanuka.microservice.dto.response.ProductResponse;
import com.shanuka.microservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        Page<ProductResponse> productPage = service.getAllProducts(page, size);
        return ResponseEntity.ok(productPage);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable("product-id") String productId
    ){
        return ResponseEntity.ok(service.getProductById(productId));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody @Valid ProductRequest request
    ){
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PutMapping("/{product-id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable("product-id") String productId,
            @RequestBody ProductUpdateRequest request
    ){
        return ResponseEntity.ok(service.updateProduct(productId, request));
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable("product-id") String productId
    ){
        service.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }


}
