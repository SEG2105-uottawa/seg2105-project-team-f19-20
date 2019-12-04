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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int x, long y) {
                EmployeeClinicInformation clinicInfo= listOfClinics.get(x);
                dialogueBox(clinicInfo.
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        //from Lab 05 Firebase implementation

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                accounts.clear();

                for(DataSnapshot accountSnapshot : dataSnapshot.getChildren()){

                    Account account = accountSnapshot.getValue(Account.class);
                    accounts.add(account);

                }
                user_item accountsAdapter = new user_item(users_view.this, accounts);
                listView.setAdapter(accountsAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }

    private void dialogueBox(final String ID, String name, String role){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialogue_delete_user, null);


        final TextView currentRole = view.findViewById(R.id.userRole);

        currentRole.setText(role);


        TextView dialogTitle = new TextView(users_view.this);
        dialogTitle.setText(name);
        dialogTitle.setGravity(Gravity.CENTER);
        dialogTitle.setTextSize(20);


        builder.setView(view)
                .setCustomTitle(dialogTitle)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        deleteUser(ID);
                        dialog.dismiss();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                    }
                });
        AlertDialog dialogue = builder.create();
        dialogue.show();
    }


    public void backClickHandler(View target) {
        Intent myIntent = new Intent(ListOfClinics.this, Search.class);
        startActivity(myIntent);
    }
}
