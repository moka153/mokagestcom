package com.mokatech.mokaGestCom.Controllers;

import com.mokatech.mokaGestCom.dtos.CategoryDto;
import com.mokatech.mokaGestCom.reponses.ResponseHandler;
import com.mokatech.mokaGestCom.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v5/categories")
@RequiredArgsConstructor
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Object> getAllCategories(){
        return ResponseHandler.responseBuilder("categories found...",
                HttpStatus.OK,
                categoryService.findAllCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getCategoriesById(@PathVariable Long categoryId){
        return ResponseHandler.responseBuilder("category found...",
                HttpStatus.OK,
                categoryService.findCategoryById(categoryId));
    }

    @PostMapping
    public ResponseEntity<Object> saveCategory(@RequestBody @Valid CategoryDto categoryDto){
        return ResponseHandler.responseBuilder("category created...",
                HttpStatus.CREATED,
                categoryService.saveCategory(categoryDto));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Object> updateCategory(@RequestBody @Valid CategoryDto categoryDto,
                                                 @PathVariable Long categoryId){
        return ResponseHandler.responseBuilder("category updated...",
                HttpStatus.OK,
                categoryService.updateCategory(categoryDto,categoryId));
    }
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
    }
}
