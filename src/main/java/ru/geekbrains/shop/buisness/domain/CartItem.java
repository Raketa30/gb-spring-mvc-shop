package ru.geekbrains.shop.buisness.domain;

import ru.geekbrains.shop.buisness.domain.dto.ProductDto;

public class CartItem {
    private final ProductDto product;
    private Integer quantity;

    public CartItem(ProductDto product) {
        this.product = product;
        this.quantity = 1;
    }

    public CartItem(ProductDto product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductDto getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void addOneItem() {
        this.quantity++;
    }

    public void substractOneItem() {
        if (quantity > 0) {
            quantity--;
        }
    }
}
