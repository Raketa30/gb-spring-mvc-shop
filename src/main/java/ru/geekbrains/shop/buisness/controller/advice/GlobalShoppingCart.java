package ru.geekbrains.shop.buisness.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.geekbrains.shop.buisness.component.ShoppingCart;

@ControllerAdvice
public class GlobalShoppingCart {

    @ModelAttribute("shoppingCart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }
}
