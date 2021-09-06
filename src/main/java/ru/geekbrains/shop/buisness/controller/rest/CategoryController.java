package ru.geekbrains.shop.buisness.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ru.geekbrains.shop.buisness.domain.constant.RequestNameConstant.API_V1;
import static ru.geekbrains.shop.buisness.domain.constant.RequestNameConstant.PRODUCT;

@RestController("restCategoryController")
@RequestMapping(API_V1 + PRODUCT)
public class CategoryController {

}
