package ru.geekbrains.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.shop.domain.CategoryEntity;
import ru.geekbrains.shop.domain.ProductEntity;
import ru.geekbrains.shop.domain.dto.ProductDTO;
import ru.geekbrains.shop.repository.ProductRepository;
import ru.geekbrains.shop.service.CategoryService;
import ru.geekbrains.shop.service.ProductService;
import ru.geekbrains.shop.util.FileUtils;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public ProductEntity saveWithImage(ProductDTO productDTO, MultipartFile image) {
        ProductEntity product = getProductEntityFromDTO(productDTO);
        ProductEntity savedProduct = productRepository.save(product);

        if (image != null && !image.isEmpty()) {
            Path pathImage = FileUtils.saveProductImage(image);
            savedProduct.setImageLink(pathImage.toString());

            productRepository.save(savedProduct);
        }
        return savedProduct;
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    private ProductEntity getProductEntityFromDTO(ProductDTO productDTO) {
        ProductEntity product = new ProductEntity();
        product.setTitle(productDTO.getTitle());
        product.setCost(productDTO.getCost());
        Set<CategoryEntity> categories = categoryService.findAllByIdList(productDTO.getCategoryIds());
        product.setCategories(categories);
        return product;
    }
}
