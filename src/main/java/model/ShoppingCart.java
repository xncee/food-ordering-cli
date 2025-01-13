package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private int cartId;
    private int customerId;
    private int restaurantId;
    private List<CartItem> items = new ArrayList<>();
    private LocalDate expiryDate;

    public ShoppingCart(int customerId, int restaurantId, List<CartItem> items, LocalDate expiryDate) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.items = items;
        this.expiryDate = expiryDate;
    }

    public double getTotal() {
        double total = 0;
        for (CartItem cartItem: items) {
            total += cartItem.getPrice() * cartItem.getQuantity();
        }

        return total;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
