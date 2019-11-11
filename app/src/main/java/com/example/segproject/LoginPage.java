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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginPage extends AppCompatActivity {

    DatabaseReference databaseUsers;

    EditText editTextUsername;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        databaseUsers = FirebaseDatabase.getInstance().getReference().child("users");

        editTextUsername = findViewById(R.id.Username);
        editTextPassword = findViewById(R.id.Password);

    }

    public void loginClickHandler(View target) {

        databaseUsers.addValueEventListener(new ValueEventListener() {

            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            boolean found = false;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try {
                        if (data.child("username").getValue().equals(username) && data.child("password").getValue().equals(hashPassword(password))) {
                            //it exists
                            if(isAdmin(username, password)) {
                                Intent myIntent = new Intent(LoginPage.this, admin_page.class);
                                startActivity(myIntent);
                                found = true;
                            } else {
                                String firstName = (String) data.child("firstName").getValue();
                                String role = (String) data.child("role").getValue();
                                Intent myIntent = new Intent(LoginPage.this, WelcomePage.class);
                                myIntent.putExtra("firstName", firstName);
                                myIntent.putExtra("role", role);
                                startActivity(myIntent);
                                found = true;
                            }
                        }
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
                if(!found) {
                    Toast.makeText(LoginPage.this, "Incorrect Username and/or Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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

    public boolean isAdmin(String username, String password) {
        return (username.equals("admin") && password.equals("5T5ptQ"));
    }
}
