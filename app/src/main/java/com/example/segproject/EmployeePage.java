package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EmployeePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page);
    }

    public void manageServicesClickHandler(View target) {
        Intent myIntent = getIntent();
        String id = myIntent.getStringExtra("userID");
        myIntent = new Intent(EmployeePage.this, EmployeeManageServices.class);
        myIntent.putExtra("userID", id);
        startActivity(myIntent);
    }

    public void clinicInformationClickHandler(View target) {
        Intent myIntent = getIntent();
        String id = myIntent.getStringExtra("userID");
        myIntent = new Intent(EmployeePage.this, EmployeeClinicInformation.class);
        myIntent.putExtra("userID", id);
        startActivity(myIntent);
    }

    public void acceptedInsurancePaymentClickHandler(View target) {
        Intent myIntent = getIntent();
        String id = myIntent.getStringExtra("userID");
        myIntent = new Intent(EmployeePage.this, EmployeeInsurancePayment.class);
        myIntent.putExtra("userID", id);
        startActivity(myIntent);
    }

    public void clinicWorkHoursClickHandler(View target) {
        Intent myIntent = getIntent();
        String id = myIntent.getStringExtra("userID");
        myIntent = new Intent(EmployeePage.this, EmployeeClinicWorkHours.class);
        myIntent.putExtra("userID", id);
        startActivity(myIntent);
    }

    public void backClickHandler(View target) {
        Intent myIntent = new Intent(EmployeePage.this, LoginPage.class);
        startActivity(myIntent);
    }

}
