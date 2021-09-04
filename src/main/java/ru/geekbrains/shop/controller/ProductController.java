package ru.geekbrains.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.geekbrains.shop.domain.CategoryEntity;
import ru.geekbrains.shop.domain.ProductEntity;
import ru.geekbrains.shop.domain.dto.ProductDTO;
import ru.geekbrains.shop.service.CategoryService;
import ru.geekbrains.shop.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String getProductListPage(Model model) {
        List<ProductEntity> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/form")
    public String getProductForm(Model model) {
        List<CategoryEntity> categories = categoryService.findAll();
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categoryList", categories);
        return "product/form";
    }

    @PostMapping("/form")
    public RedirectView saveProduct(ProductDTO product,
                                    @RequestParam(required = false) MultipartFile image,
                                    RedirectAttributes attributes) {
        productService.saveWithImage(product, image);
        return new RedirectView("/product/list");
    }

    @PostMapping("/delete")
    public RedirectView deleteProductById(@RequestParam Long id) {
        productService.deleteProductById(id);
        return new RedirectView("/product/list");
    }
}
