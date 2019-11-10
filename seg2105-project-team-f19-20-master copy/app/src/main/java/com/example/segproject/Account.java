package com.example.segproject;

public class Account {

    private String userID;
    private String firstName;
    private String lastName;
    private String role;
    private String username;
    private String email;
    private String password;

    public Account() {

    }

    public Account(String userID, String firstName, String lastName, String role, String username, String email, String password) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
