package com.example.segproject;

import java.util.List;
import java.util.Map;

public class Employee extends Account {

    Map<String, String> clinicInfo;
    List<String> insuranceProviders;
    List<String> paymentMethods;

    public Employee() {

    }

    public Employee(String userID, String firstName, String lastName, String role, String username, String email, String password, Map<String, String> clinicInfo, List<String> insuranceProviders, List<String> paymentMethods) {
        super(userID, firstName, lastName, role, username, email, password);
        this.clinicInfo = clinicInfo;
        this.insuranceProviders = insuranceProviders;
        this.paymentMethods = paymentMethods;
    }

    public Map<String, String> getClinicInfo() { return clinicInfo; }

    public List<String> getInsuranceProviders() { return insuranceProviders; }

    public List<String> getPaymentMethods() { return paymentMethods; }

}
