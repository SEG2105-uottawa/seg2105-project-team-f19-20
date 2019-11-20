package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.NoSuchAlgorithmException;

public class EmployeeClinicInformation extends AppCompatActivity {

    DatabaseReference databaseClinics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_clinic_information);

        final TextView currentAddress = (TextView) findViewById(R.id.currentAddress);
        final TextView currentPhone = (TextView) findViewById(R.id.currentPhone);
        final TextView currentName = (TextView) findViewById(R.id.currentName);

        Intent myIntent = getIntent();
        final String id = myIntent.getStringExtra("userID");

        databaseClinics = FirebaseDatabase.getInstance().getReference().child("users");

        databaseClinics.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("userID").getValue().equals(id)) {
                        //it exists
                        if(data.child("clinicInfo").child("address").getValue().equals("")) {
                            currentAddress.setText("Clinic address is currently empty.");
                        } else {
                            currentAddress.setText((String) data.child("clinicInfo").child("address").getValue());
                        }
                        if(data.child("clinicInfo").child("phone").getValue().equals("")) {
                            currentPhone.setText("Clinic phone number is currently empty.");
                        } else {
                            currentPhone.setText((String) data.child("clinicInfo").child("phone").getValue());
                        }
                        if(data.child("clinicInfo").child("name").getValue().equals("")) {
                            currentName.setText("Clinic name is currently empty.");
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
        Intent myIntent = new Intent(EmployeeClinicInformation.this, EmployeeManageServices.class);
        startActivity(myIntent);
    }

    public void changePhoneClickHandler(View target) {
        Intent myIntent = new Intent(EmployeeClinicInformation.this, EmployeeManageServices.class);
        startActivity(myIntent);
    }

    public void changeNameClickHandler(View target) {
        Intent myIntent = new Intent(EmployeeClinicInformation.this, EmployeeManageServices.class);
        startActivity(myIntent);
    }


}
