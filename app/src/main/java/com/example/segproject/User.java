package com.example.segproject;

public class User {

    String firstName;
    String lastName;
    String role;
    String username;
    String password;
    String userID;

    public User() {

    }

    public User(String userID, String firstName, String lastName, String role, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.username = username;
        this.password = password;
        this.userID = userID;
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

    public String getPassword() {
        return password;
    }

    public String getUserID() {
        return userID;
    }
}
