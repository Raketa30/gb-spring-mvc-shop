package ru.geekbrains.shop.buisness.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.geekbrains.shop.buisness.component.ShoppingCart;
import ru.geekbrains.shop.buisness.domain.CartItem;
import ru.geekbrains.shop.buisness.domain.dto.ProductDto;
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
        ProductDto productDto = productService.getProductDtoById(id);
        shoppingCart.addCartItem(new CartItem(productDto));
        return new RedirectView("/product/list");
    }

    @GetMapping("/delete-from-cart")
    public RedirectView deleteProductFromCart(Long id, @ModelAttribute ShoppingCart shoppingCart) {
        shoppingCart.deleteCartItem(id);
        return new RedirectView("/product/list");
    }

    @GetMapping("/plus-one")
    public RedirectView addOneProduct(Long id, @ModelAttribute ShoppingCart shoppingCart) {
        shoppingCart.addCartItemOnePc(id);
        return new RedirectView("/cart");
    }

    @GetMapping("/minus-one")
    public RedirectView extractOneProduct(Long id, @ModelAttribute ShoppingCart shoppingCart) {
        shoppingCart.deleteCartItemOnePc(id);
        return new RedirectView("/cart");
    }
}
