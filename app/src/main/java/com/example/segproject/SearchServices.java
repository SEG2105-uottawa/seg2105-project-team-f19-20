package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchServices extends AppCompatActivity {

    EditText Service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_services);
        Service = (EditText) findViewById(R.id.SearchServices);
    }

    public void backClickHandler(View target) {
        Intent myIntent = new Intent(SearchServices.this, Search.class);
        startActivity(myIntent);
    }
}