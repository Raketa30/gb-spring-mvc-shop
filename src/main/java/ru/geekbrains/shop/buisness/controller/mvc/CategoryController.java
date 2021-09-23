package ru.geekbrains.shop.buisness.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.shop.buisness.domain.CategoryEntity;
import ru.geekbrains.shop.buisness.domain.dto.CategoryDto;
import ru.geekbrains.shop.buisness.service.CategoryService;

import java.util.List;

import static ru.geekbrains.shop.buisness.domain.constant.RequestNameConstant.CATEGORY;

@Controller
@RequestMapping(CATEGORY)
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String getCategoryPage(Model model) {
        List<CategoryDto> categories = categoryService.findAllDto();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/admin/list")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    public String getCategoryAdminListPage(Model model) {
        List<CategoryEntity> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "category/admin-list";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    public String addCategory(CategoryDto categoryDTO) {
        categoryService.addCategory(categoryDTO);
        return "redirect:/category/list";
    }
}
