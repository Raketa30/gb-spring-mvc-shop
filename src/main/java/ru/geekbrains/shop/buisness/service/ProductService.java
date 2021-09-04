package ru.geekbrains.shop.buisness.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.shop.buisness.domain.ProductEntity;
import ru.geekbrains.shop.buisness.domain.dto.ProductDTO;

import java.util.List;

@Service
public interface ProductService {
    List<ProductEntity> getAllProducts();

    ProductEntity saveWithImage(ProductDTO product, MultipartFile image);

    void deleteProductById(Long id);
}

