package com.mokatech.mokaGestCom.services;

import com.mokatech.mokaGestCom.dtos.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
    void deleteCategory(Long categoryId);
    Optional<CategoryDto> findCategoryById(Long categoryId);
    List<CategoryDto> findAllCategories();
}
