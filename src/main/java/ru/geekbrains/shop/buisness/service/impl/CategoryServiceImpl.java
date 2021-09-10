package ru.geekbrains.shop.buisness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.shop.buisness.domain.CategoryEntity;
import ru.geekbrains.shop.buisness.domain.dto.CategoryDto;
import ru.geekbrains.shop.buisness.repository.CategoryRepository;
import ru.geekbrains.shop.buisness.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public CategoryEntity addCategory(CategoryDto categoryDTO) {
        CategoryEntity entity = new CategoryEntity();
        entity.setTitle(categoryDTO.getTitle());
        entity.setAlias(categoryDTO.getAlias());
        entity.setParentCategory(categoryRepository.findCategoryByTitle(categoryDTO.getParentCategory()));
        return categoryRepository.save(entity);
    }

    @Override
    public Set<CategoryEntity> findAllByIdList(List<Long> categoryIds) {
        return categoryRepository.findAllByIdIn(categoryIds);
    }

    @Override
    public List<Long> getCategoryIdList(Set<CategoryEntity> categories) {
        List<Long> ids = new ArrayList<>();
        categories.forEach(category -> ids.add(category.getId()));
        return ids;
    }

    @Override
    public List<CategoryDto> findAllDto() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        return getCategoryDtoListFromEntityList(categoryEntities);
    }

    private List<CategoryDto> getCategoryDtoListFromEntityList(List<CategoryEntity> categoryEntities) {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntities) {
            categoryDtos.add(getCategoryDto(categoryEntity));
        }
        return categoryDtos;
    }

    private CategoryDto getCategoryDto(CategoryEntity entity) {
        return CategoryDto.builder().id(entity.getId())
                .title(entity.getTitle())
                .alias(entity.getAlias())
                .parentCategory(entity.getParentCategory().getTitle())
                .build();
    }
}
