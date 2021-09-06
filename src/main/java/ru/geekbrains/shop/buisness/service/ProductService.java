package ru.geekbrains.shop.buisness.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.shop.buisness.domain.ProductEntity;
import ru.geekbrains.shop.buisness.domain.dto.ProductDto;
import ru.geekbrains.shop.buisness.search.ProductSearchCondition;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<ProductEntity> getAllProducts();

    ProductEntity saveWithImage(ProductDto product, MultipartFile image);

    void deleteProductById(Long id);

    Page<ProductDto> findAllPaginated(ProductSearchCondition condition);

    ProductDto getProductDtoById(Long id);

    ProductEntity updateWithImage(ProductDto productDto, MultipartFile image);

    Optional<ProductEntity> getProductById(Long id);
}

