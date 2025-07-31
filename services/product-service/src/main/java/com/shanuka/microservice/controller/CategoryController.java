package com.shanuka.microservice.controller;

import com.shanuka.microservice.dto.request.CategoryRequest;
import com.shanuka.microservice.dto.response.CategoryResponse;
import com.shanuka.microservice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        return ResponseEntity.ok(service.getAllCategories());
    }

    @PostMapping("/{category-id}")
    public ResponseEntity<CategoryResponse> saveCategory(
            @PathVariable("category-id") String categoryId,
            @RequestBody @Valid CategoryRequest request
    ){
        return ResponseEntity.ok(service.saveCategory(request));
    }

}
