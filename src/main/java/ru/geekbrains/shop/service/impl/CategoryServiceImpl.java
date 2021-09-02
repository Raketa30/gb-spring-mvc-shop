package ru.geekbrains.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.shop.domain.CategoryEntity;
import ru.geekbrains.shop.repository.CategoryRepository;
import ru.geekbrains.shop.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }
}
