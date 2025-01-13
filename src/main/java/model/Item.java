package model;

import model.enums.ItemCategory;
import model.users.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Item {
    public static class ItemBuilder {
        private final String itemId;
        private final Restaurant restaurant;
        private final ItemCategory category;
        private final String title;
        private final double price;

        private String description;
        private List<String> tags = new ArrayList<>();
        private String image;
        private List<String> customizations = new ArrayList<>();
        private double discount;
        private double finalPrice;
        private boolean isAvailable = true;

        public ItemBuilder(String itemId, Restaurant restaurant, ItemCategory category, String title, double price) {
            this.itemId = itemId;
            this.restaurant = restaurant;
            this.category = category;
            this.title = title;
            this.price = price;
        }

        public ItemBuilder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public ItemBuilder image(String image) {
            this.image = image;
            return this;
        }

        public ItemBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ItemBuilder customizations(List<String> customizations) {
            this.customizations = customizations;
            return this;
        }

        public ItemBuilder discount(double discount) {
            this.discount = discount;
            return this;
        }

        public ItemBuilder finalPrice(double finalPrice) {
            this.finalPrice = finalPrice;
            return this;
        }

        public ItemBuilder isAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }

    private String itemId;
    private Restaurant restaurant;
    private ItemCategory category;
    private List<String> tags;
    private String image;
    private String title;
    private String description;
    private List<String> customizations;
    private double price;
    private double discount;
    private double finalPrice;

    private boolean isAvailable;

    // Private constructor to enforce the use of the ItemBuilder
    private Item(ItemBuilder itemBuilder) {
        this.itemId = itemBuilder.itemId;
        this.restaurant = itemBuilder.restaurant;
        this.category = itemBuilder.category;
        this.tags = itemBuilder.tags;
        this.image = itemBuilder.image;
        this.title = itemBuilder.title;
        this.description = itemBuilder.description;
        this.customizations = itemBuilder.customizations;
        this.price = itemBuilder.price;
        this.discount = itemBuilder.discount;
        this.finalPrice = itemBuilder.finalPrice;
        this.isAvailable = itemBuilder.isAvailable;
    }
}