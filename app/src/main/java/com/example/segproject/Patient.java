package com.example.segproject;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Patient extends Account {

    DatabaseReference databaseUsers;
    int waitTime;
    List<EmployeeClinicInformation> listOfClinics;
    public Patient() {

    }

    public Patient(String userID, String firstName, String lastName, String role, String username, String email, String password) {
        super(userID, firstName, lastName, role, username, email, password);
    }


    public int getWaitTime(){
        return waitTime;
    }

}
