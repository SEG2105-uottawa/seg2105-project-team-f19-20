package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class admin_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
    }

    public void manageServicesClickHandler(View target) {
        Intent myIntent = new Intent(admin_page.this, services_view.class);
        startActivity(myIntent);
    }

    public void manageUsersClickHandler(View target) {
        Intent myIntent = new Intent(admin_page.this, users_view.class);
        startActivity(myIntent);
    }

    public void backClickHandler(View target) {
        Intent myIntent = new Intent(admin_page.this, LoginPage.class);
        startActivity(myIntent);
    }

}
