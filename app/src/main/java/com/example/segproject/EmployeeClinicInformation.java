package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.NoSuchAlgorithmException;

public class EmployeeClinicInformation extends AppCompatActivity {

    DatabaseReference databaseUsers;
    EditText currentAddress;
    EditText currentPhone;
    EditText currentName;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_clinic_information);

        currentAddress = (EditText) findViewById(R.id.currentAddress);
        currentPhone = (EditText) findViewById(R.id.currentPhone);
        currentName = (EditText) findViewById(R.id.currentName);

        Intent myIntent = getIntent();
        id = myIntent.getStringExtra("userID");

        databaseUsers = FirebaseDatabase.getInstance().getReference().child("users");

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("userID").getValue().equals(id)) {
                        //it exists
                        if(data.child("clinicInfo").child("address").getValue().equals("")) {
                            currentAddress.setHint("Currently empty!");
                        } else {
                            currentAddress.setText((String) data.child("clinicInfo").child("address").getValue());
                        }
                        if(data.child("clinicInfo").child("phone").getValue().equals("")) {
                            currentPhone.setHint("Currently empty!");
                        } else {
                            currentPhone.setText((String) data.child("clinicInfo").child("phone").getValue());
                        }
                        if(data.child("clinicInfo").child("name").getValue().equals("")) {
                            currentName.setHint("Currently empty!");
                        } else {
                            currentName.setText((String) data.child("clinicInfo").child("name").getValue());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void changeAddressClickHandler(View target) {
        if(invalidAddress(currentAddress.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter a valid address.", Toast.LENGTH_SHORT).show();
        } else {
            databaseUsers.child(id).child("clinicInfo").child("address").setValue(currentAddress.getText().toString().trim());
            Toast.makeText(getApplicationContext(), "Address changed", Toast.LENGTH_LONG).show();
        }
    }

    public void changePhoneClickHandler(View target) {
        if(invalidPhoneNumber(currentPhone.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
        } else {
            databaseUsers.child(id).child("clinicInfo").child("phone").setValue(currentPhone.getText().toString().trim());
            Toast.makeText(getApplicationContext(), "Phone number changed", Toast.LENGTH_LONG).show();
        }
    }

    public void changeNameClickHandler(View target) {
        if(invalidName(currentName.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter a valid name.", Toast.LENGTH_SHORT).show();
        } else {
            databaseUsers.child(id).child("clinicInfo").child("name").setValue(currentName.getText().toString().trim());
            Toast.makeText(getApplicationContext(), "Clinic name changed", Toast.LENGTH_LONG).show();
        }
    }

    public boolean invalidAddress(String str) {
        //Retrieved regex expression from StackOverFlow
        //Question title: Regular expression for address field validation

        return !str.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z.]+)");
    }

    public boolean invalidPhoneNumber(String str) {
        //Retrieved regex expression from StackOverFlow
        //Question title: Java Regular Expressions to Validate phone numbers

        return !str.matches("^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$");
    }

    public boolean invalidName(String str) {
        //Retrieved regex expression from StackOverFlow
        //Question title: Java Regex to Validate Full Name allow only Spaces and Letters

        return !str.matches("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}");
    }

    public void backClickHandler(View target) {
        Intent myIntent = new Intent(EmployeeClinicInformation.this, EmployeePage.class);
        startActivity(myIntent);
    }
}