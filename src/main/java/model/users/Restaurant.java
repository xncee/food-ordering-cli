package model.users;

import model.Item;
import model.enums.UserType;

import java.util.List;

public class Restaurant extends User {
    public static class RestaurantBuilder extends UserBuilder<RestaurantBuilder> {
        private String restaurantName;
        private String restaurantCategory;
        private String location;
        private List<Item> menuItems;
        private float rating;
        private String description;
        private String image;

        public RestaurantBuilder(Integer id, String username, String fullName, String email, String phoneNumber, String hashedPassword) {
            super(id, UserType.RESTAURANT.toString(), username, fullName, email, phoneNumber, hashedPassword);
        }

        public RestaurantBuilder restaurantName(String restaurantName) {
            this.restaurantName = restaurantName;
            return this;
        }
        public RestaurantBuilder restaurantCategory(String restaurantCategory) {
            this.restaurantCategory = restaurantCategory;
            return this;
        }
        public RestaurantBuilder location(String location) {
            this.location = location;
            return this;
        }
        public RestaurantBuilder menuItems(List<Item> menuItems) {
            this.menuItems = menuItems;
            return this;
        }
        public RestaurantBuilder rating(float rating) {
            this.rating = rating;
            return this;
        }
        public RestaurantBuilder image(String image) {
            this.image = image;
            return this;
        }

        public RestaurantBuilder description(String description) {
            this.description = description;
            return this;
        }
        @Override
        protected RestaurantBuilder self() {
            return this;
        }

        @Override
        public Restaurant build() {
            return new Restaurant(this);
        }
    }

    private String restaurantName;
    private String restaurantCategory;
    private String location;
    private List<Item> menuItems;
    private float rating;
    private String description;
    private String image;

    private Restaurant(RestaurantBuilder builder) {
        super(builder);
        this.restaurantName = builder.restaurantName;
        this.restaurantCategory = builder.restaurantCategory;
        this.location = builder.location;
        this.menuItems = builder.menuItems;
        this.rating = builder.rating;
        this.image = builder.image;
        this.description = builder.description;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRestaurantCategory() {
        return restaurantCategory;
    }

    public void setRestaurantCategory(String restaurantCategory) {
        this.restaurantCategory = restaurantCategory;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Item> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<Item> menuItems) {
        this.menuItems = menuItems;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return super.toString()+"\nRestaurant{" +
                "restaurantName='" + restaurantName + '\'' +
                ", restaurantCategory='" + restaurantCategory + '\'' +
                ", location='" + location + '\'' +
                ", menuItems=" + menuItems +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
