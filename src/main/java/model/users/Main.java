package model.users;

import model.Address;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserFactory factory = new CustomerFactory();
        List<Address> addresses = new ArrayList<>();

        Customer.CustomerBuilder builder = (Customer.CustomerBuilder) factory.create(123, "xnce", "Saif Alkhalili", "xnceee@gmail.com", "+962796027099", "hashedpassword1");
        Customer customer = builder.balance(80)
                .orderHistory(new ArrayList<>())
                .savedAddresses(addresses)
                .creditCards(new ArrayList<>())
                .build();
        System.out.println(customer);
    }
}
