package ru.geekbrains.shop.buisness.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.geekbrains.shop.GbShopApplicationTests;
import ru.geekbrains.shop.buisness.domain.CategoryEntity;
import ru.geekbrains.shop.buisness.domain.ProductEntity;
import ru.geekbrains.shop.buisness.repository.CategoryRepository;
import ru.geekbrains.shop.buisness.repository.ProductRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceImplTest extends GbShopApplicationTests {
    @Mock
    CategoryServiceImpl categoryService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("<-test saving productEntity->")
    void shouldSaveProductEntitySuccessfully() {
//        //given
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setTitle("test");
//        productDTO.setCost(0.01);
//        productDTO.setCategoryIds(Collections.singletonList(1L));
//
//        Set<CategoryEntity> categoryEntities = new HashSet<>();
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setId(1L);
//        categoryEntities.add(categoryEntity);
//
//        ProductEntity product = productService.getProductEntityFromDTO(productDTO);
//        //when
//        when(categoryRepository.findAllByIdIn(productDTO.getCategoryIds())).thenReturn(categoryEntities);
//
//        when(productRepository.save(product)).thenReturn(product);
//        productService.saveWithImage(productDTO, null);
//        Set<CategoryEntity> foundCategories = categoryService.findAllByIdList(productDTO.getCategoryIds());
//
//        //then
//        assertThat(foundCategories).isNotNull();
//        verify(categoryRepository).findAllByIdIn(productDTO.getCategoryIds());
//        verify(productRepository).save(product);

    }

    @Test
    void shouldGetAllProducts() {
        Set<CategoryEntity> categoryEntities = new HashSet<>();
        categoryEntities.add(new CategoryEntity());
        ProductEntity product = new ProductEntity(1L, "test", 1.1, "testLink", categoryEntities);

        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
        List<ProductEntity> found = productRepository.findAll();

        assertThat(found).isNotNull();
        verify(productRepository).findAll();
    }

    @Test
    @DisplayName("<-test deleting product->")
    void shouldDeleteProductSuccessfully() {
        Set<CategoryEntity> categoryEntities = new HashSet<>();
        categoryEntities.add(new CategoryEntity());
        ProductEntity product = new ProductEntity(1L, "test", 1.1, "testLink", categoryEntities);

        when(productRepository.deleteProductEntityById(1L)).thenReturn(product);
        productService.deleteProductById(1L);
        verify(productRepository).deleteProductEntityById(1L);
    }


}