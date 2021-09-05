package ru.geekbrains.shop.buisness.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.shop.buisness.domain.ProductEntity;
import ru.geekbrains.shop.buisness.domain.dto.ProductDto;

import java.util.List;

@Service
public interface ProductService {
    List<ProductEntity> getAllProducts();

    ProductEntity saveWithImage(ProductDto product, MultipartFile image);

    void deleteProductById(Long id);

    Page<ProductEntity> findAllPaginated(Pageable pageRequest);

    ProductDto getProductDtoById(Long id);
}

