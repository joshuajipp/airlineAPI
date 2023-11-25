package com.ENSF480.airlineBackend.user;

public class User {
    private String email;
    private String firstName;
    private String lastName;

    public User() {
    }

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(User user) {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}
