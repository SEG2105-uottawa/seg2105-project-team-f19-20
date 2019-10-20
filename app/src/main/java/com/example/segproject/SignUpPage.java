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

        editTextFirstName = findViewById(R.id.FirstName);
        editTextLastName = findViewById(R.id.LastName);
        spinner = findViewById(R.id.RoleSelector);
        editTextUsername = findViewById(R.id.Username);
        editTextPassword = findViewById(R.id.Password);
        buttonSignUp = findViewById(R.id.SignUpButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Roles));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void signupClickHandler(View target) throws NoSuchAlgorithmException {
        addUser();
        hashPassword("password");
        Intent myIntent = new Intent(SignUpPage.this, LoginPage.class);
        startActivity(myIntent);
    }

    public void addUser() throws NoSuchAlgorithmException {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String role = spinner.getSelectedItem().toString();
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(!TextUtils.isEmpty(firstName) || !TextUtils.isEmpty(lastName) || !TextUtils.isEmpty(username) || !TextUtils.isEmpty(password)) {

            String id = databaseUsers.push().getKey();

            User user = new User(id, firstName, lastName, role, username, hashPassword(password));

            databaseUsers.child(id).setValue(user);

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

}
