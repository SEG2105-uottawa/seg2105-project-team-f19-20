package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.NoSuchAlgorithmException;

public class add_service extends AppCompatActivity {

    EditText serviceName;
    Spinner spinner;
    Button submitService;

    DatabaseReference databaseServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        serviceName = findViewById(R.id.ServiceName);
        spinner = findViewById(R.id.RoleSelectorService);
        submitService = findViewById(R.id.SubmitService);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ServiceRoles));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void serviceClickHandler(View target) throws NoSuchAlgorithmException {
        addService();
    }

    public void addService() throws NoSuchAlgorithmException {

        String name = serviceName.getText().toString().trim();
        String role = spinner.getSelectedItem().toString();

        Intent myIntent = new Intent(add_service.this, services_view.class);

        if(!TextUtils.isEmpty(name)){


            if(invalidName(name)) {
                Toast.makeText(this, "Please enter a valid Service Name", Toast.LENGTH_LONG).show();
            }
            else{

                String id = databaseServices.push().getKey();

                Service service = new Service(name, role, id);
                System.out.println("before database addition");


                databaseServices.child(id).setValue(service);

                System.out.println("past database addition");

                startActivity(myIntent);


            }
        }
        else {
            Toast.makeText(this, "Please make sure all fields are filled", Toast.LENGTH_SHORT).show();
        }

    }
    public boolean invalidName(String str) {
        return !str.matches("[a-zA-Z]+");
    }
    public void backClickHandler(View target) throws NoSuchAlgorithmException {

        Intent myIntent = new Intent(add_service.this, services_view.class);

        startActivity(myIntent);

    }


}
