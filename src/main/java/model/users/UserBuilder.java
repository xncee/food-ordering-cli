package model.users;

public abstract class UserBuilder<T> {
    protected final Integer id;
    protected final String userType;
    protected final String username;
    protected final String fullName;
    protected final String email;
    protected final String phoneNumber;
    protected final String hashedPassword;
    protected double balance;

    public UserBuilder(Integer id, String userType, String username, String fullName, String email, String phoneNumber, String hashedPassword) {
        this.id = id;
        this.userType = userType;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hashedPassword = hashedPassword;
    }

    public T balance(double balance) {
        this.balance = balance;
        return self();
    }

    protected abstract T self();
    public abstract User build();
}