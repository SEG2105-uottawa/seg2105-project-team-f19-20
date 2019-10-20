package com.example.segproject;

public class Patient extends Account {

    public Patient() {

    }

    public Patient(String userID, String firstName, String lastName, String role, String username, String email, String password) {
        super(userID, firstName, lastName, role, username, email, password);
    }

}
