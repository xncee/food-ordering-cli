package model;

public class Address {
    public static class Builder {
        private String name;
        private String country;
        private String city;
        private String line1;
        private String line2; // Optional
        private String phoneNumber; // Optional
        private String additionalInstructions; // Optional
        private boolean isDefault; // Default is false

        public Builder(String name, String country, String city, String line1) {
            this.name = name;
            this.country = country;
            this.city = city;
            this.line1 = line1;
        }

        public Builder line2(String line2) {
            this.line2 = line2;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder additionalInstructions(String additionalInstructions) {
            this.additionalInstructions = additionalInstructions;
            return this;
        }

        public Builder isDefault(boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

    private String name;
    private String country;
    private String city;
    private String line1;
    private String line2;
    private String phoneNumber;
    private String additionalInstructions;
    private boolean isDefault;

    private Address(Builder builder) {
        this.name = builder.name;
        this.country = builder.country;
        this.city = builder.city;
        this.line1 = builder.line1;
        this.line2 = builder.line2;
        this.phoneNumber = builder.phoneNumber;
        this.additionalInstructions = builder.additionalInstructions;
        this.isDefault = builder.isDefault;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAdditionalInstructions() {
        return additionalInstructions;
    }

    public boolean isDefault() {
        return isDefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", additionalInstructions='" + additionalInstructions + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}