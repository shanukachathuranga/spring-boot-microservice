package com.shanuka.microservice.mapper;

import com.shanuka.microservice.dto.request.CategoryRequest;
import com.shanuka.microservice.dto.response.CategoryResponse;
import com.shanuka.microservice.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public CategoryResponse toCategoryResponse(Category category){
        return new CategoryResponse(
                category.getId().toString(),
                category.getName(),
                category.getDescription()
        );
    }

    public Category toCategory(CategoryRequest request){
        return Category.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }

}
