package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class StartupPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_page);
    }

//    Button loginButton = (Button) findViewById(R.id.LoginButton);
//    Button signupButton = (Button) findViewById(R.id.SignUpButton);

    public void loginClickHandler(View target) {
        Intent myIntent = new Intent(StartupPage.this, LoginPage.class);
        startActivity(myIntent);
    }

    public void signupClickHandler(View target) {
        Intent myIntent = new Intent(StartupPage.this, SignUpPage.class);
        startActivity(myIntent);
    }

}
