package ru.geekbrains.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.shop.domain.CategoryEntity;
import ru.geekbrains.shop.domain.dto.CategoryDTO;
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

    @Override
    @Transactional
    public CategoryEntity addCategory(CategoryDTO categoryDTO) {
        CategoryEntity entity = new CategoryEntity();
        entity.setTitle(categoryDTO.getTitle());
        entity.setAlias(categoryDTO.getAlias());
        entity.setParentCategory(categoryDTO.getParentCategory());
        return categoryRepository.save(entity);
    }
}
