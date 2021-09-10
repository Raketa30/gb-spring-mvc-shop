package ru.geekbrains.shop.buisness.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.geekbrains.shop.buisness.component.ShoppingCart;
import ru.geekbrains.shop.buisness.domain.ProductEntity;
import ru.geekbrains.shop.buisness.service.ProductService;

@Controller
@SessionAttributes("shoppingCart")
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ProductService productService;

    @Autowired
    public ShoppingCartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getCartList(@ModelAttribute ShoppingCart shoppingCart) {
        return "cart/list";
    }

    @GetMapping("/add-to-cart")
    public RedirectView addProductToCart(Long id, @ModelAttribute ShoppingCart shoppingCart) {
        ProductEntity productEntity = productService.getProductById(id);
        shoppingCart.addCartItem(productEntity);
        return new RedirectView("/product/list");
    }

    @GetMapping("/delete-from-cart")
    public RedirectView deleteProductFromCart(Long id) {
        return new RedirectView("/product/list");
    }

    @GetMapping("/plus-one")
    public RedirectView addOneProduct(Long id, @ModelAttribute ShoppingCart shoppingCart) {
        ProductEntity productEntity = productService.getProductById(id);
        shoppingCart.addCartItem(productEntity);
        return new RedirectView("/cart/list");
    }

    @GetMapping("/minus-one")
    public RedirectView extractOneProduct(Long id, @ModelAttribute ShoppingCart shoppingCart) {
        ProductEntity productEntity = productService.getProductById(id);
        shoppingCart.addCartItem(productEntity);
        return new RedirectView("/cart/list");
    }
}
