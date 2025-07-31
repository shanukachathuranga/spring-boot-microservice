package com.shanuka.microservice.mapper;

import com.shanuka.microservice.dto.request.ProductRequest;
import com.shanuka.microservice.dto.response.ProductResponse;
import com.shanuka.microservice.model.Category;
import com.shanuka.microservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductMapper {

    public ProductResponse toProductResponse(Product product){
        return new ProductResponse(
                product.getId().toString(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory().getId().toString(),
                product.getCategory().getName(),
                product.isAvailable()
        );
    }

    public Product toProduct(ProductRequest request){
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .stockQuantity(request.stockQuantity())
                .isAvailable(request.stockQuantity() > 0)
                .category(
                        Category.builder()
                                .id(UUID.fromString(request.categoryId()))
                                .build()
                )
                .build();
    }

}
