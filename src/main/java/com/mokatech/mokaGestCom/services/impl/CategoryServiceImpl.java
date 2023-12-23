package com.mokatech.mokaGestCom.services.impl;

import com.mokatech.mokaGestCom.dtos.CategoryDto;
import com.mokatech.mokaGestCom.entities.Category;
import com.mokatech.mokaGestCom.exceptions.ConflictException;
import com.mokatech.mokaGestCom.exceptions.EntityNotFoundException;
import com.mokatech.mokaGestCom.mappers.CategoryMapper;
import com.mokatech.mokaGestCom.repositories.CategoryRepository;
import com.mokatech.mokaGestCom.services.CategoryService;
import jakarta.persistence.UniqueConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        try{
            Category savedCategory = categoryRepository.save(category);
            return categoryMapper.categoryToCategoryDto(savedCategory);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("Category name already exists...");
        }
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        if(categoryRepository.findById(categoryId).isEmpty()){
            throw new EntityNotFoundException("Category with id: "+categoryId+" does not exists...");
        }
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        category.setCategoryId(categoryId);
        try{
            Category updatedCategory = categoryRepository.save(category);
            return categoryMapper.categoryToCategoryDto(updatedCategory);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("Category name already exists...");
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        try {
            if(categoryRepository.findById(categoryId).isEmpty()){
                throw new EntityNotFoundException("Category with id: "+categoryId+" does not exists...");
            }
            categoryRepository.deleteById(categoryId);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("category already used...");
        }
    }

    @Override
    public Optional<CategoryDto> findCategoryById(Long categoryId) {
        return Optional.ofNullable(categoryMapper.categoryToCategoryDto(
                                categoryRepository.findById(categoryId).orElseThrow(() ->
                                                new EntityNotFoundException("category with id: "+categoryId+" does not exists"))));
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
    }
}
