package ru.geekbrains.shop.buisness.service;

import ru.geekbrains.shop.buisness.domain.CategoryEntity;
import ru.geekbrains.shop.buisness.domain.dto.CategoryDto;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    List<CategoryEntity> findAll();

    CategoryEntity addCategory(CategoryDto categoryDTO);

    Set<CategoryEntity> findAllByIdList(List<Long> categoryIds);

    List<Long> getCategoryIdList(Set<CategoryEntity> categories);

    List<CategoryDto> findAllDto();
}
