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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpPage extends AppCompatActivity {

    EditText editTextFirstName;
    EditText editTextLastName;
    Spinner spinner;
    EditText editTextUsername;
    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonSignUp;

    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        editTextFirstName = findViewById(R.id.FirstName);
        editTextLastName = findViewById(R.id.LastName);
        spinner = findViewById(R.id.RoleSelector);
        editTextUsername = findViewById(R.id.Username);
        editTextEmail = findViewById(R.id.Email);
        editTextPassword = findViewById(R.id.Password);
        buttonSignUp = findViewById(R.id.SignUpButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Roles));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void backClickHandler(View target) {
        Intent myIntent = new Intent(SignUpPage.this, StartupPage.class);
        startActivity(myIntent);
    }

    public void signupClickHandler(View target) throws NoSuchAlgorithmException {
        addUser();
    }

    public void addUser() throws NoSuchAlgorithmException {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String role = spinner.getSelectedItem().toString();
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        Intent myIntent = new Intent(SignUpPage.this, LoginPage.class);

        if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            if(invalidName(firstName)) {
                Toast.makeText(this, "Please enter a valid First Name", Toast.LENGTH_LONG).show();
            } else if(invalidName(lastName)) {
                Toast.makeText(this, "Please enter a valid Last Name", Toast.LENGTH_LONG).show();
            } else if(invalidEmail(email)) {
                Toast.makeText(this, "Please enter a valid Email", Toast.LENGTH_LONG).show();
            }

            else if(role.equals("Patient")) {
                String id = databaseUsers.push().getKey();

                Patient user = new Patient(id, firstName, lastName, role, username, email, hashPassword(password));

                databaseUsers.child(id).setValue(user);

                startActivity(myIntent);
            } else if(role.equals("Employee")) {
                String id = databaseUsers.push().getKey();

                Map<String, String> clinicInfo = new HashMap<>();

                clinicInfo.put("address", "");
                clinicInfo.put("phone", "");
                clinicInfo.put("name", "");

                Map<String, String> workHours = new HashMap<>();

                workHours.put("Monday", "");
                workHours.put("Tuesday", "");
                workHours.put("Wednesday", "");
                workHours.put("Thursday", "");
                workHours.put("Friday", "");

                List<String> insuranceProviders = new ArrayList<>();
                List<String> paymentMethods = new ArrayList<>();

                List<String> services = new ArrayList<>();

                Employee user = new Employee(id, firstName, lastName, role, username, email, hashPassword(password), clinicInfo, insuranceProviders, paymentMethods, workHours, services);

                databaseUsers.child(id).setValue(user);

                startActivity(myIntent);
            }

        } else {
            Toast.makeText(this, "Please make sure all fields are filled", Toast.LENGTH_SHORT).show();
        }
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        String hashedPassword = "";
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] b = md.digest();
        for(int i = 0; i < b.length; i++) {
            hashedPassword += Integer.toHexString(b[i] & 0xff);
        }
        return hashedPassword;
    }

    public boolean invalidName(String str) {
        return !str.matches("[a-zA-Z]+");
    }

    public boolean invalidEmail(String email) {
        return !email.matches("^[0-9a-zA-Z.%_+-]+@[0-9a-zA-Z.-]+\\.[a-zA-Z]{2,8}$");
    }

}
