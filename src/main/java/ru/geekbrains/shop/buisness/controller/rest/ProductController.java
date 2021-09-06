package ru.geekbrains.shop.buisness.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.shop.buisness.domain.dto.ProductDto;
import ru.geekbrains.shop.buisness.search.ProductSearchCondition;
import ru.geekbrains.shop.buisness.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.geekbrains.shop.buisness.domain.constant.RequestNameConstant.API_V1;
import static ru.geekbrains.shop.buisness.domain.constant.RequestNameConstant.PRODUCT;

@RestController("restProductController")
@RequestMapping(API_V1 + PRODUCT)
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public ResponseEntity<ProductSearchCondition> getProductListByCondition(ProductSearchCondition condition) {
        Page<ProductDto> page = productService.findAllPaginated(condition);
        condition.setPage(page);

        int totalPages = page.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            condition.setPageNumbers(pageNumbers);
        }
        return new ResponseEntity<>(condition, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto productDto = productService.getProductDtoById(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PutMapping(path = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> updateProduct(@RequestBody ProductDto productDto,
                                              @RequestParam(required = false) MultipartFile image) {
        productService.updateWithImage(productDto, image);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public void deleteProductById(@RequestBody Long id) {
        productService.deleteProductById(id);
    }

}
