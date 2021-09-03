package ru.geekbrains.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.shop.domain.CategoryEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private String title;
    private String alias;
    private CategoryEntity parentCategory;
}
