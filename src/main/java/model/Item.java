package model;

import model.enums.ItemCategory;

import java.util.ArrayList;
import java.util.List;

public class Item {
    public static class ItemBuilder {
        private final Integer itemId;
        private final Integer restaurantId;
        private final String category;
        private final String title;
        private final double price;

        private String description;
        private List<String> tags = new ArrayList<>();
        private String image;
        private List<String> customizations = new ArrayList<>();
        private double discount;
        private double finalPrice;
        private boolean isAvailable = true;

        public ItemBuilder(Integer itemId, Integer restaurantId, String category, String title, double price) {
            this.itemId = itemId;
            this.restaurantId = restaurantId;
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

    private Integer itemId;
    private Integer restaurantId;
    private String category;
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
        this.restaurantId = itemBuilder.restaurantId;
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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCustomizations() {
        return customizations;
    }

    public void setCustomizations(List<String> customizations) {
        this.customizations = customizations;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}