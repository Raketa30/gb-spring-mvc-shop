package ru.geekbrains.shop.buisness.component;

import lombok.NoArgsConstructor;
import ru.geekbrains.shop.buisness.domain.ProductEntity;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class ShoppingCart {
    private final Map<ProductEntity, Integer> cartItems = new HashMap<>();
    private Double totalPrice = 0.0;

    public void addCartItem(ProductEntity item) {
        cartItems.merge(item, 1, (prev, cur) -> prev + 1);
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        totalPrice = 0.0;
        for (Map.Entry<ProductEntity, Integer> entry : cartItems.entrySet()) {
            totalPrice += entry.getKey().getCost() * entry.getValue();
        }
    }

    public void removeCartItem(ProductEntity item) {
        if (cartItems.get(item) > 1) {
            cartItems.merge(item, cartItems.get(item), (prev, cur) -> prev - 1);
        }
        cartItems.remove(item);
        updateTotalPrice();
    }

    public Map<ProductEntity, Integer> getCartItems() {
        return new HashMap<>(cartItems);
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public int getCount() {
        return cartItems.values().stream()
                .reduce(0, Integer::sum);
    }
}
