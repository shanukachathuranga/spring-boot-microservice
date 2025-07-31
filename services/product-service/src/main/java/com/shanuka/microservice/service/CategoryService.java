package com.shanuka.microservice.service;

import com.shanuka.microservice.dto.request.CategoryRequest;
import com.shanuka.microservice.dto.response.CategoryResponse;
import com.shanuka.microservice.mapper.CategoryMapper;
import com.shanuka.microservice.repository.CategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public List<CategoryResponse> getAllCategories() {
        return repository.findAll()
                .stream().map(mapper::toCategoryResponse)
                .toList();
    }

    public CategoryResponse saveCategory(@Valid CategoryRequest request) {
        var category = mapper.toCategory(request);
        return mapper.toCategoryResponse(repository.save(category));
    }
}
