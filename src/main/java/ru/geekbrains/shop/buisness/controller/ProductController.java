package ru.geekbrains.shop.buisness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.geekbrains.shop.buisness.domain.CategoryEntity;
import ru.geekbrains.shop.buisness.domain.ProductEntity;
import ru.geekbrains.shop.buisness.domain.dto.ProductDto;
import ru.geekbrains.shop.buisness.service.CategoryService;
import ru.geekbrains.shop.buisness.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public String getProductListPage(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            Model model
    ) {
        Pageable pageRequest = PageRequest.of(pageNum - 1, pageSize);
        Page<ProductEntity> page = productService.findAllPaginated(pageRequest);

        int totalPages = page.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        return "product/list";
    }

    @GetMapping("/form")
    public String getProductForm(Model model) {
        List<CategoryEntity> categories = categoryService.findAll();
        model.addAttribute("productDTO", new ProductDto());
        model.addAttribute("categoryList", categories);
        return "product/add";
    }

    @PostMapping("/form")
    public RedirectView saveProduct(ProductDto productDto,
                                    @RequestParam(required = false) MultipartFile image,
                                    RedirectAttributes attributes) {
        productService.saveWithImage(productDto, image);
        return new RedirectView("/product/list");
    }

    @PostMapping("/update")
    public RedirectView updateProduct(@ModelAttribute ProductDto productDto,
                                      @RequestParam(required = false) MultipartFile image,
                                      RedirectAttributes attributes) {
        productService.updateWithImage(productDto, image);
        return new RedirectView("/product/list");
    }

    @PostMapping("/delete")
    public RedirectView deleteProductById(@RequestParam Long id) {
        productService.deleteProductById(id);
        return new RedirectView("/product/list");
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        ProductDto productDTO = productService.getProductDtoById(id);
        List<CategoryEntity> categories = categoryService.findAll();
        model.addAttribute("product", productDTO);
        model.addAttribute("categoryList", categories);
        return "product/update";
    }
}
