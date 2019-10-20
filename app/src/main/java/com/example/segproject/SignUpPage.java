package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPage extends AppCompatActivity {

    EditText editTextFirstName;
    EditText editTextLastName;
    Spinner spinner;
    EditText editTextUsername;
    EditText editTextPassword;
    Button buttonSignUp;

    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        editTextFirstName = (EditText) findViewById(R.id.FirstName);
        editTextLastName = (EditText) findViewById(R.id.LastName);
        spinner = (Spinner) findViewById(R.id.RoleSelector);
        editTextUsername = (EditText) findViewById(R.id.Username);
        editTextPassword = (EditText) findViewById(R.id.Password);
        buttonSignUp = (Button) findViewById(R.id.SignUpButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Roles));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                //saving selected item to variable "item"
//                String item = adapterView.getItemAtPosition(i).toString();
//
//                Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }

    public void signupClickHandler(View target) {
        addUser();
        Intent myIntent = new Intent(SignUpPage.this, LoginPage.class);
        startActivity(myIntent);
    }

    public void addUser() {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String role = spinner.getSelectedItem().toString();
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(!TextUtils.isEmpty(firstName) || !TextUtils.isEmpty(lastName) || !TextUtils.isEmpty(username) || !TextUtils.isEmpty(password)) {

            String id = databaseUsers.push().getKey();

            User user = new User(id, firstName, lastName, role, username, password);

            databaseUsers.child(id).setValue(user);

        } else {
            Toast.makeText(this, "Please make sure all fields are filled", Toast.LENGTH_SHORT).show();
        }
    }

}
