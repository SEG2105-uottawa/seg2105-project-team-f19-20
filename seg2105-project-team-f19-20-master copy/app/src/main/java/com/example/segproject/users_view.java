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

public class users_view extends AppCompatActivity {


    ListView listView;

    List<Account> accounts;

    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_view);


        databaseUsers = FirebaseDatabase.getInstance().getReference("users");


        listView = findViewById(R.id.listUsers);

        accounts = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int x, long y) {
                Account account = accounts.get(x);
                dialogueBox(account.getUserID(), account.getFirstName(), account.getRole());
            }
        });



    }
    @Override
    protected void onStart() {
        super.onStart();


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


    private void deleteUser(String id) {

        DatabaseReference reference = databaseUsers.child(id);

        reference.removeValue();

        Toast.makeText(getApplicationContext(), "User deleted", Toast.LENGTH_LONG).show();

    }
    public void backClickHandler(View target) throws NoSuchAlgorithmException {

        Intent myIntent = new Intent(users_view.this, admin_page.class);

        startActivity(myIntent);

    }


}
