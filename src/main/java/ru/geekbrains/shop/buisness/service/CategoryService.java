package ru.geekbrains.shop.buisness.service;

import ru.geekbrains.shop.buisness.domain.CategoryEntity;
import ru.geekbrains.shop.buisness.domain.dto.CategoryDTO;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    List<CategoryEntity> findAll();

    CategoryEntity addCategory(CategoryDTO categoryDTO);

    Set<CategoryEntity> findAllByIdList(List<Long> categoryIds);
}
