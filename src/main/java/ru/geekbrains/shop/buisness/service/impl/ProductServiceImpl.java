package ru.geekbrains.shop.buisness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.shop.buisness.domain.ProductEntity;
import ru.geekbrains.shop.buisness.domain.dto.ProductDto;
import ru.geekbrains.shop.buisness.repository.ProductRepository;
import ru.geekbrains.shop.buisness.service.CategoryService;
import ru.geekbrains.shop.buisness.service.ProductService;
import ru.geekbrains.shop.util.FileUtils;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

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
    public ProductEntity saveWithImage(ProductDto productDto, MultipartFile image) {
        ProductEntity product = getProductEntityFromDTO(productDto);
        ProductEntity savedProduct = productRepository.save(product);

        if (image != null && !image.isEmpty()) {
            Path pathImage = FileUtils.saveProductImage(image);
            savedProduct.setImageLink(pathImage.toString());

            productRepository.save(savedProduct);
        }
        return savedProduct;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductEntity> findAllPaginated(Pageable pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    @Override
    public ProductDto getProductDtoById(Long id) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);

        if (optionalProductEntity.isPresent()) {
            return getProductDtoFromEntity(optionalProductEntity.get());
        }

        return new ProductDto();
    }

    private ProductDto getProductDtoFromEntity(ProductEntity entity) {
        return ProductDto.builder().id(entity.getId())
                .title(entity.getTitle())
                .cost(entity.getCost())
                .categoryIds(categoryService.getCategoryIdList(entity.getCategories()))
                .build();
    }

    public ProductEntity getProductEntityFromDTO(ProductDto dto) {
        return ProductEntity.builder().title(dto.getTitle())
                .cost(dto.getCost())
                .categories(categoryService.findAllByIdList(dto.getCategoryIds()))
                .build();
    }
}
