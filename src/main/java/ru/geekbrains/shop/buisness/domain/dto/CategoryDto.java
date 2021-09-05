package ru.geekbrains.shop.buisness.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.shop.buisness.domain.CategoryEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;
    private String alias;
    private CategoryEntity parentCategory;
}
