package com.example.segproject;


import com.google.firebase.database.DatabaseReference;

public class Patient extends Account {

    DatabaseReference databaseUsers;

    int rateService;
    int rateClinic;
    int waitTime;
    int bookAppointment;
    EmployeeClinicInformation searchAddress;
    EmployeeClinicInformation searchHours:
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

    public EmployeeClinicInformation

    public com.example.segproject.EmployeeClinicInformation getSearchAddress() {
        return searchAddress;
    }
}
