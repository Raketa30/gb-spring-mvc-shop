package ru.geekbrains.shop.buisness.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.shop.buisness.domain.dto.CategoryDto;
import ru.geekbrains.shop.buisness.service.CategoryService;

import java.util.List;

import static ru.geekbrains.shop.buisness.domain.constant.RequestNameConstant.API_V1;
import static ru.geekbrains.shop.buisness.domain.constant.RequestNameConstant.CATEGORY;

@RestController("restCategoryController")
@RequestMapping(API_V1 + CATEGORY)
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDto>> getGategoryListPage() {
        List<CategoryDto> categoryEntityList = categoryService.findAllDto();
        return new ResponseEntity<>(categoryEntityList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(CategoryDto categoryDTO) {
        categoryService.addCategory(categoryDTO);
        return ResponseEntity.ok().build();
    }
}
