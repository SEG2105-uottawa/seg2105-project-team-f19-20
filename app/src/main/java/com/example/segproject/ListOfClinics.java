package com.example.segproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ListOfClinics extends AppCompatActivity {


    ListView listView;
    List<EmployeeClinicInformation> listOfClinics;

    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_clinics);

        //from Lab 05 Firebase implementation

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        
        listView = findViewById(R.id.Clinics);

        listOfClinics = new ArrayList<>();

    }
    @Override
    protected void onStart() {
        super.onStart();

        //from Lab 05 Firebase implementation
    }


    public void backClickHandler(View target) {
        Intent myIntent = new Intent(ListOfClinics.this, Search.class);
        startActivity(myIntent);
    }
}
