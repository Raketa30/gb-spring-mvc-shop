package ru.geekbrains.shop.service;

import ru.geekbrains.shop.domain.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> findAll();
}
