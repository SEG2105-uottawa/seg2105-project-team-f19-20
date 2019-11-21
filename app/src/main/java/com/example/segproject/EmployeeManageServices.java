package com.example.segproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManageServices extends AppCompatActivity {

    String id;

    ListView listViewEmployeeServices;

    List<String> employeeServicesArray;
    List<String> adminServicesAvailable;

    DatabaseReference databaseUsers;
    DatabaseReference databaseServices;

    Spinner servicesDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_manage_services);

        databaseUsers = FirebaseDatabase.getInstance().getReference().child("users");
        databaseServices = FirebaseDatabase.getInstance().getReference().child("services");

        Intent myIntent = getIntent();
        id = myIntent.getStringExtra("userID");

        listViewEmployeeServices = findViewById(R.id.listServiceEmployee);

        employeeServicesArray = new ArrayList<>();
        adminServicesAvailable = new ArrayList<>();

        adminServicesAvailable.add("");

        servicesDropdown = findViewById(R.id.servicesDropdown);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, adminServicesAvailable);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        servicesDropdown.setAdapter(adapter);

        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot serviceSnapshot : dataSnapshot.getChildren()){
                    Service service = serviceSnapshot.getValue(Service.class);
                    assert service != null;
                    adminServicesAvailable.add(service.getName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //dialogue stuff
        listViewEmployeeServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int x, long y) {
                String service = employeeServicesArray.get(x);
                dialogueBox(service);
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

                employeeServicesArray.clear();

                for(DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("userID").getValue().equals(id)) {
                        //it exists
                        if(data.child("services") != null) {
                            for(DataSnapshot elem : data.child("services").getChildren()) {
                                employeeServicesArray.add(elem.getValue(String.class));
                            }
                        }
                    }
                }

                InsurancePaymentItem servicesAdapter = new InsurancePaymentItem(EmployeeManageServices.this, employeeServicesArray);
                listViewEmployeeServices.setAdapter(servicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }

    private void dialogueBox(final String name){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogue_delete_insurance_payment, null);

        TextView dialogTitle = new TextView(this);
        dialogTitle.setText(name);
        dialogTitle.setGravity(Gravity.CENTER);
        dialogTitle.setTextSize(20);

        builder.setView(view)
                .setCustomTitle(dialogTitle)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        deleteService(name);
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
        Intent myIntent = new Intent(EmployeeManageServices.this, EmployeePage.class);
        startActivity(myIntent);
    }

    public void addServiceClickHandler(View target) {
        addService();
    }

    public void addService() {
        employeeServicesArray.add(servicesDropdown.getSelectedItem().toString());
        databaseUsers.child(id).child("services").setValue(employeeServicesArray);
        Toast.makeText(getApplicationContext(), "Service added", Toast.LENGTH_LONG).show();
    }

    private void deleteService(String name) {
        employeeServicesArray.remove(name);
        databaseUsers.child(id).child("services").setValue(employeeServicesArray);
        Toast.makeText(getApplicationContext(), "Service deleted", Toast.LENGTH_LONG).show();
    }
}
