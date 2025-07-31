package com.shanuka.microservice.service;

import com.shanuka.microservice.dto.request.ProductRequest;
import com.shanuka.microservice.dto.request.ProductUpdateRequest;
import com.shanuka.microservice.dto.response.ProductResponse;
import com.shanuka.microservice.exception.BusinessException;
import com.shanuka.microservice.mapper.ProductMapper;
import com.shanuka.microservice.model.Product;
import com.shanuka.microservice.repository.CategoryRepository;
import com.shanuka.microservice.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    public Page<ProductResponse> getAllProducts(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = repository.findAll(pageable);
        return productPage.map(mapper::toProductResponse);

    }


    public ProductResponse getProductById(String productId) {

        var response = repository.findById(UUID.fromString(productId))
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("product not found with id: " + productId));

        // throw error if product not available
        if (!response.isAvailable()){
            throw new BusinessException("product: " + productId + ", not available");
        }
        return response;
    }

    public ProductResponse createProduct(@Valid ProductRequest request) {
        var product = mapper.toProduct(request);
        return mapper.toProductResponse(repository.save(product));
    }


    public ProductResponse updateProduct(String productId, ProductUpdateRequest request) {

        var product = repository.findById(UUID.fromString(productId))
                .orElseThrow(() -> new BusinessException("product: "+ productId +" not found"));

        if (!request.name().isEmpty() && !request.name().isBlank()){
            product.setName(request.name());
        }
        if (!request.description().isEmpty() && !request.description().isBlank()){
            product.setDescription(request.description());
        }
        if (request.price() != null){
            product.setPrice(request.price());
        }
        if (request.stockQuantity() != null){
            product.setStockQuantity(request.stockQuantity());
        }
        if (request.categoryId() != null && !request.categoryId().isEmpty() && !request.categoryId().isBlank()){
            var category = categoryRepository.findById(UUID.fromString(request.categoryId()))
                    .orElseThrow(() -> new BusinessException("category: " + request.categoryId() + " not found"));
            product.setCategory(category);
        }
        product.setAvailable(request.isAvailable());

        return mapper.toProductResponse(repository.save(product));
    }

    public void deleteProduct(String productId) {
        repository.deleteById(UUID.fromString(productId));
    }
}

