package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.security.NoSuchAlgorithmException;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        TextView greeting = (TextView) findViewById(R.id.Greeting);

        Intent myIntent = getIntent();
        String name = myIntent.getStringExtra("firstName");
        String role = myIntent.getStringExtra("role");


        if(role.equals("Patient")) {
            greeting.setText("Welcome " + name + "! \nYou are logged in as a " + role);
        } else {
            greeting.setText("Welcome " + name + "! \nYou are logged in as an " + role);
        }

    }

    public void startSearchClickHandler(View target) {
        Intent myIntent;
        myIntent = new Intent(WelcomePage.this, Search.class);
        startActivity(myIntent);
    }
}
