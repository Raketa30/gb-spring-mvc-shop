package ru.geekbrains.shop.buisness.component;

import lombok.NoArgsConstructor;
import ru.geekbrains.shop.buisness.domain.CartItem;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class ShoppingCart {
    private final Map<Long, CartItem> cartItems = new HashMap<>();
    private Double totalPrice = 0.0;

    public void addCartItem(CartItem cartItem) {
        Long productId = cartItem.getProduct().getId();
        if (cartItems.containsKey(productId)) {
            cartItems.get(productId).addOneItem();
        }
        cartItems.put(productId, cartItem);
        updateTotalPrice();
    }

    public void addCartItemOnePc(Long productId) {
        if (cartItems.containsKey(productId)) {
            cartItems.get(productId).addOneItem();
        }
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void deleteCartItem(Long productId) {
        cartItems.remove(productId);
        updateTotalPrice();
    }

    public void deleteCartItemOnePc(Long productId) {
        if (cartItems.containsKey(productId)) {
            if (cartItems.get(productId).getQuantity() == 1) {
                deleteCartItem(productId);
                return;
            }
            cartItems.get(productId).substractOneItem();
        }
        updateTotalPrice();
    }

    public Map<Long, CartItem> getCartItems() {
        return new HashMap<>(cartItems);
    }

    private void updateTotalPrice() {
        totalPrice = 0.0;
        for (CartItem cartItem : cartItems.values()) {
            totalPrice += (cartItem.getProduct().getCost() * cartItem.getQuantity());
        }
    }

    public int getCount() {
        int count = 0;
        for (CartItem cartItem : cartItems.values()) {
            count += cartItem.getQuantity();
        }
        return count;
    }

}
