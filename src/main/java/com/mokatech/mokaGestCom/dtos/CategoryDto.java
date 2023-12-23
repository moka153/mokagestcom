package com.mokatech.mokaGestCom.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Long categoryId;
    @NotBlank(message = "category name is required...")
    private String categoryName;
    private String picture;
    private String description;
}
