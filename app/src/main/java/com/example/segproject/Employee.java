package com.example.segproject;

import java.util.List;
import java.util.Map;

public class Employee extends Account {

    Map<String, String> clinicInfo;
    List<String> insuranceProviders;
    List<String> paymentMethods;
    Map<String, String> workHours;
    List<String> services;

    public Employee() {

    }

    public Employee(String userID, String firstName, String lastName, String role, String username, String email, String password, Map<String, String> clinicInfo, List<String> insuranceProviders, List<String> paymentMethods, Map<String, String> workHours, List<String> services) {
        super(userID, firstName, lastName, role, username, email, password);
        this.clinicInfo = clinicInfo;
        this.insuranceProviders = insuranceProviders;
        this.paymentMethods = paymentMethods;
        this.workHours = workHours;
        this.services = services;
    }

    public Map<String, String> getClinicInfo() { return clinicInfo; }

    public List<String> getInsuranceProviders() { return insuranceProviders; }

    public List<String> getPaymentMethods() { return paymentMethods; }

    public Map<String, String> getWorkHours() { return workHours; }

    public List<String> getServices() { return  services; }

}
