package com.example.segproject;


import com.google.firebase.database.DatabaseReference;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Patient extends Account {

    DatabaseReference databaseUsers;

    int rateService;
    int rateClinic;
    int waitTime;
    int bookAppointment;
    EmployeeClinicInformation searchAddress;
    EmployeeClinicInformation searchHours;
    EmployeeClinicInformation searchService;
    public Patient() {

    }

    public Patient(String userID, String firstName, String lastName, String role, String username, String email, String password) {
        super(userID, firstName, lastName, role, username, email, password);
    }


    public int getWaitTime(){ return waitTime;
    }

    public void rateService(int rating){

    }

    public void rateClinic(int rating){

    }

    public com.example.segproject.EmployeeClinicInformation getSearchHours() {
        return searchHours;
    }

    public com.example.segproject.EmployeeClinicInformation getSearchAddress() {
        return searchAddress;
    }

    public com.example.segproject.EmployeeClinicInformation getSearchService() {
        return searchService;
    }
}
