package ru.geekbrains.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.shop.domain.CategoryEntity;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String title;
    private Double cost;
    private Set<CategoryEntity> categories;
}
