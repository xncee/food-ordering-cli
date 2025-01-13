package model;

import java.time.LocalDateTime;

public class Order {
    public static class OrderBuilder {
        private int orderId;
        private int restaurantId;
        private int customerId;
        private int deliveryId;
        private String orderType;
        private ShoppingCart shoppingCart;
        private Promo promoCode;
        private double discount;
        private double total;
        private String paymentMethod;
        private String paymentStatus;
        private LocalDateTime placedAt;
        private LocalDateTime acceptedAt;
        private LocalDateTime preparedAt;
        private LocalDateTime completedAt;
        private Review review;
        private String status;
        private boolean isCanceled;
        private String cancellationReason;

        public OrderBuilder(int orderId, int restaurantId, int customerId, int deliveryId, String orderType, ShoppingCart shoppingCart, double total, String paymentMethod, String paymentStatus, LocalDateTime placedAt, String status) {
            this.orderId = orderId;
            this.restaurantId = restaurantId;
            this.customerId = customerId;
            this.deliveryId = deliveryId;
            this.orderType = orderType;
            this.shoppingCart = shoppingCart;
            this.total = total;
            this.paymentMethod = paymentMethod;
            this.paymentStatus = paymentStatus;
            this.placedAt = placedAt;
            this.status = status;
        }

        public OrderBuilder orderId(int orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderBuilder restaurantId(int restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public OrderBuilder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public OrderBuilder deliveryId(int deliveryId) {
            this.deliveryId = deliveryId;
            return this;
        }

        public OrderBuilder orderType(String orderType) {
            this.orderType = orderType;
            return this;
        }

        public OrderBuilder shoppingCart(ShoppingCart shoppingCart) {
            this.shoppingCart = shoppingCart;
            return this;
        }

        public OrderBuilder promoCode(Promo promoCode) {
            this.promoCode = promoCode;
            return this;
        }

        public OrderBuilder discount(double discount) {
            this.discount = discount;
            return this;
        }

        public OrderBuilder total(double total) {
            this.total = total;
            return this;
        }

        public OrderBuilder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public OrderBuilder paymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public OrderBuilder placedAt(LocalDateTime placedAt) {
            this.placedAt = placedAt;
            return this;
        }

        public OrderBuilder acceptedAt(LocalDateTime acceptedAt) {
            this.acceptedAt = acceptedAt;
            return this;
        }

        public OrderBuilder preparedAt(LocalDateTime preparedAt) {
            this.preparedAt = preparedAt;
            return this;
        }

        public OrderBuilder completedAt(LocalDateTime completedAt) {
            this.completedAt = completedAt;
            return this;
        }

        public OrderBuilder review(Review review) {
            this.review = review;
            return this;
        }

        public OrderBuilder status(String status) {
            this.status = status;
            return this;
        }

        public OrderBuilder isCanceled(boolean isCanceled) {
            this.isCanceled = isCanceled;
            return this;
        }

        public OrderBuilder cancellationReason(String cancellationReason) {
            this.cancellationReason = cancellationReason;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    private int orderId;
    private int restaurantId;
    private int customerId;
    private int deliveryId;
    private String orderType;
    private ShoppingCart shoppingCart;
    private Promo promoCode;
    private double discount;
    private double total;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDateTime placedAt;
    private LocalDateTime acceptedAt;
    private LocalDateTime preparedAt;
    private LocalDateTime completedAt;
    private Review review;
    private String status;
    private boolean isCanceled;
    private String cancellationReason;

    private Order(OrderBuilder builder) {
        this.orderId = builder.orderId;
        this.restaurantId = builder.restaurantId;
        this.customerId = builder.customerId;
        this.deliveryId = builder.deliveryId;
        this.orderType = builder.orderType;
        this.shoppingCart = builder.shoppingCart;
        this.promoCode = builder.promoCode;
        this.discount = builder.discount;
        this.total = builder.total;
        this.paymentMethod = builder.paymentMethod;
        this.paymentStatus = builder.paymentStatus;
        this.placedAt = builder.placedAt;
        this.acceptedAt = builder.acceptedAt;
        this.preparedAt = builder.preparedAt;
        this.completedAt = builder.completedAt;
        this.review = builder.review;
        this.status = builder.status;
        this.isCanceled = builder.isCanceled;
        this.cancellationReason = builder.cancellationReason;
    }

    // Getter and setter methods
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Promo getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(Promo promoCode) {
        this.promoCode = promoCode;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(LocalDateTime placedAt) {
        this.placedAt = placedAt;
    }

    public LocalDateTime getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(LocalDateTime acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    public LocalDateTime getPreparedAt() {
        return preparedAt;
    }

    public void setPreparedAt(LocalDateTime preparedAt) {
        this.preparedAt = preparedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }
}