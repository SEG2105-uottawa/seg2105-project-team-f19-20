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

public class SearchAddressPage extends AppCompatActivity {

    EditText Address;
    DatabaseReference databaseUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_address);
        databaseUsers = FirebaseDatabase.getInstance().getReference().child("users");
        Address = (EditText) findViewById(R.id.TextAddress);
    }


    public void searchForClinic(View target){
        String address = Address.getText().toString();
        Intent myIntent = new Intent(SearchAddressPage.this, ListOfClinics.class);
        myIntent.putExtra("Address", address);
        startActivity(myIntent);
    }

    public void backClickHandler(View target) {
        Intent myIntent = new Intent(SearchAddressPage.this, Search.class);
        startActivity(myIntent);
    }


}
