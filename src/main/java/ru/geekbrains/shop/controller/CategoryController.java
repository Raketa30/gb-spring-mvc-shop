package ru.geekbrains.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.shop.domain.CategoryEntity;
import ru.geekbrains.shop.domain.dto.CategoryDTO;
import ru.geekbrains.shop.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String getGategoryListPage(Model model) {
        List<CategoryEntity> categoryEntityList = categoryService.findAll();
        model.addAttribute("categories", categoryEntityList);
        return "category/list";
    }

    @PostMapping("/add")
    public String addCategory(CategoryDTO categoryDTO) {
        categoryService.addCategory(categoryDTO);
        return "redirect:/category/list";
    }
}