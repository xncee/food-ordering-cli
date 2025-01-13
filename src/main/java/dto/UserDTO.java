package dto;

import model.users.Customer;
import view.CustomerPanel;

public class UserDTO {
    private String id; // Optional during creation
    private String username;
    private String email;
    private String phoneNumber;
    private String fullName;
    private String password;
    private Customer customer;

    private UserDTO(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.fullName = builder.fullName;
        this.password = builder.password;
        this.customer = builder.customer;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String username;
        private String email;
        private String phoneNumber;
        private String fullName;
        private String password;
        private Customer customer;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }
        public UserDTO build() {
            return new UserDTO(this);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}