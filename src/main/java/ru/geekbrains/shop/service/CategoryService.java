package ru.geekbrains.shop.service;

import ru.geekbrains.shop.domain.CategoryEntity;
import ru.geekbrains.shop.domain.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> findAll();

    CategoryEntity addCategory(CategoryDTO categoryDTO);

}
