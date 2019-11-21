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
import com.google.firebase.database.ValueEventListener;

public class EmployeeClinicWorkHours extends AppCompatActivity {

    DatabaseReference databaseUsers;
    EditText currentMondayHours;
    EditText currentTuesdayHours;
    EditText currentWednesdayHours;
    EditText currentThursdayHours;
    EditText currentFridayHours;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_clinic_work_hours);

        currentMondayHours = (EditText) findViewById(R.id.currentMondayHours);
        currentTuesdayHours = (EditText) findViewById(R.id.currentTuesdayHours);
        currentWednesdayHours = (EditText) findViewById(R.id.currentWednesdayHours);
        currentThursdayHours = (EditText) findViewById(R.id.currentThursdayHours);
        currentFridayHours = (EditText) findViewById(R.id.currentFridayHours);

        Intent myIntent = getIntent();
        id = myIntent.getStringExtra("userID");

        databaseUsers = FirebaseDatabase.getInstance().getReference().child("users");

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("userID").getValue().equals(id)) {
                        //it exists
                        if(data.child("workHours").child("Monday").getValue().equals("")) {
                            currentMondayHours.setHint("ex. 7am - 8pm");
                        } else {
                            currentMondayHours.setText((String) data.child("workHours").child("Monday").getValue());
                        }
                        if(data.child("workHours").child("Tuesday").getValue().equals("")) {
                            currentTuesdayHours.setHint("ex. 7am - 8pm");
                        } else {
                            currentTuesdayHours.setText((String) data.child("workHours").child("Tuesday").getValue());
                        }
                        if(data.child("workHours").child("Wednesday").getValue().equals("")) {
                            currentWednesdayHours.setHint("ex. 7am - 8pm");
                        } else {
                            currentWednesdayHours.setText((String) data.child("workHours").child("Wednesday").getValue());
                        }
                        if(data.child("workHours").child("Thursday").getValue().equals("")) {
                            currentThursdayHours.setHint("ex. 7am - 8pm");
                        } else {
                            currentThursdayHours.setText((String) data.child("workHours").child("Thursday").getValue());
                        }
                        if(data.child("workHours").child("Friday").getValue().equals("")) {
                            currentFridayHours.setHint("ex. 7am - 8pm");
                        } else {
                            currentFridayHours.setText((String) data.child("workHours").child("Friday").getValue());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void changeMondayHoursClickHandler(View target) {
        if(invalidHours(currentMondayHours.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter valid hours.", Toast.LENGTH_SHORT).show();
        } else {
            databaseUsers.child(id).child("workHours").child("Monday").setValue(currentMondayHours.getText().toString().trim());
            Toast.makeText(getApplicationContext(), "Hours changed", Toast.LENGTH_LONG).show();
        }
    }

    public void changeTuesdayHoursClickHandler(View target) {
        if(invalidHours(currentTuesdayHours.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter valid hours.", Toast.LENGTH_SHORT).show();
        } else {
            databaseUsers.child(id).child("workHours").child("Tuesday").setValue(currentTuesdayHours.getText().toString().trim());
            Toast.makeText(getApplicationContext(), "Hours changed", Toast.LENGTH_LONG).show();
        }
    }

    public void changeWednesdayHoursClickHandler(View target) {
        if(invalidHours(currentWednesdayHours.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter valid hours.", Toast.LENGTH_SHORT).show();
        } else {
            databaseUsers.child(id).child("workHours").child("Wednesday").setValue(currentWednesdayHours.getText().toString().trim());
            Toast.makeText(getApplicationContext(), "Hours changed", Toast.LENGTH_LONG).show();
        }
    }

    public void changeThursdayHoursClickHandler(View target) {
        if(invalidHours(currentThursdayHours.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter valid hours.", Toast.LENGTH_SHORT).show();
        } else {
            databaseUsers.child(id).child("workHours").child("Thursday").setValue(currentThursdayHours.getText().toString().trim());
            Toast.makeText(getApplicationContext(), "Hours changed", Toast.LENGTH_LONG).show();
        }
    }

    public void changeFridayHoursClickHandler(View target) {
        if(invalidHours(currentFridayHours.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter valid hours.", Toast.LENGTH_SHORT).show();
        } else {
            databaseUsers.child(id).child("workHours").child("Friday").setValue(currentFridayHours.getText().toString().trim());
            Toast.makeText(getApplicationContext(), "Hours changed", Toast.LENGTH_LONG).show();
        }
    }

    public boolean invalidHours(String str) {
        return !str.matches("[0-9]{1,2}(am|pm|AM|PM)\\s?[-]\\s?[0-9]{1,2}(am|pm|AM|PM)");
    }
}
