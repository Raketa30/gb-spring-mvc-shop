package ru.geekbrains.shop.buisness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.shop.buisness.domain.ProductEntity;
import ru.geekbrains.shop.buisness.domain.dto.ProductDto;
import ru.geekbrains.shop.buisness.repository.ProductRepository;
import ru.geekbrains.shop.buisness.search.ProductSearchCondition;
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
        return getProductEntity(image, product);
    }

    @Override
    @Transactional
    public ProductEntity updateWithImage(ProductDto productDto, MultipartFile image) {
        ProductEntity product = productRepository.getById(productDto.getId());
        updateProduct(product, productDto);
        return getProductEntity(image, product);
    }

    @Override
    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    private ProductEntity getProductEntity(MultipartFile image, ProductEntity product) {
        ProductEntity savedProduct = productRepository.save(product);

        if (image != null && !image.isEmpty()) {
            Path pathImage = FileUtils.saveProductImage(image);
            savedProduct.setImageLink(pathImage.toString());

            productRepository.save(savedProduct);
        }
        return savedProduct;
    }

    private void updateProduct(ProductEntity product, ProductDto dto) {
        product.setTitle(dto.getTitle());
        product.setCost(dto.getCost());
        product.setCategories(categoryService.findAllByIdList(dto.getCategoryIds()));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductDto> findAllPaginated(ProductSearchCondition condition) {
        Pageable pageRequest = PageRequest.of(
                condition.getPageNum() - 1,
                condition.getPageSize(),
                Sort.by(condition.getSortDirection(), condition.getSortField())
        );

        Page<ProductEntity> productEntityPage = productRepository.findAll(pageRequest);
        return productEntityPage.map(this::getProductDtoFromEntity);
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
                .imageLink(entity.getImageLink())
                .categoryIds(categoryService.getCategoryIdList(entity.getCategories()))
                .build();
    }

    private ProductEntity getProductEntityFromDTO(ProductDto dto) {
        return ProductEntity.builder().title(dto.getTitle())
                .cost(dto.getCost())
                .categories(categoryService.findAllByIdList(dto.getCategoryIds()))
                .build();
    }
}
