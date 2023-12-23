package com.mokatech.mokaGestCom.mappers;

import com.mokatech.mokaGestCom.dtos.CategoryDto;
import com.mokatech.mokaGestCom.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category categoryDtoToCategory(CategoryDto categoryDto){
        return Category.builder()
                .categoryId(categoryDto.getCategoryId())
                .categoryName(categoryDto.getCategoryName())
                .picture(categoryDto.getPicture())
                .description(categoryDto.getDescription())
                .build();
    }

    public CategoryDto categoryToCategoryDto(Category category){
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .picture(category.getPicture())
                .description(category.getDescription())
                .build();
    }
}
