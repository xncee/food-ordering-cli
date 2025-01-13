package model.enums;

public enum UserType {
    RESTAURANT,
    DRIVER,
    CUSTOMER,
    ADMIN;

    @Override
    public String toString() {
        return name().toUpperCase();
    }
}