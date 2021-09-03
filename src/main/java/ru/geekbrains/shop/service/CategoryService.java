package ru.geekbrains.shop.service;

import ru.geekbrains.shop.domain.CategoryEntity;
import ru.geekbrains.shop.domain.dto.CategoryDTO;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    List<CategoryEntity> findAll();

    CategoryEntity addCategory(CategoryDTO categoryDTO);

    Set<CategoryEntity> findAllByIdList(List<Long> categoryIds);
}
