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
import android.widget.Button;
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

public class services_view extends AppCompatActivity {

    ListView listView;

    List<Service> services;

    DatabaseReference databaseServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_view);

        //from Lab 05 Firebase implementation


        databaseServices = FirebaseDatabase.getInstance().getReference("services");


        listView = findViewById(R.id.listService);

        services = new ArrayList<>();

        //dialogue stuff
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int x, long y) {
                Service service = services.get(x);
                dialogueBox(service.getID(), service.getName(), service.getRole());
            }
        });



    }
    @Override
    protected void onStart() {
        super.onStart();

        //from Lab 05 Firebase implementation

        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                services.clear();

                for(DataSnapshot serviceSnapshot : dataSnapshot.getChildren()){

                    Service service = serviceSnapshot.getValue(Service.class);
                    services.add(service);

                }
                service_item servicesAdapter = new service_item(services_view.this, services);
                listView.setAdapter(servicesAdapter);

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
        View view = inflater.inflate(R.layout.activity_dialogue_edit, null);


        final TextView currentRole = view.findViewById(R.id.currentRole);
        final EditText editName = view.findViewById(R.id.editName);
        final Spinner spinner  = view.findViewById(R.id.spinnerEditRole);

        currentRole.setText(role);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ServiceRoles));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView dialogTitle = new TextView(this);
        dialogTitle.setText(name);
        dialogTitle.setGravity(Gravity.CENTER);
        dialogTitle.setTextSize(20);


        builder.setView(view)
                .setCustomTitle(dialogTitle)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        deleteService(ID);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String name = editName.getText().toString().trim();
                        String role = spinner.getSelectedItem().toString();

                        if (!TextUtils.isEmpty(name)) {
                            if(invalidName(name)) {
                                Toast.makeText(services_view.this, "Please enter a valid Service Name", Toast.LENGTH_LONG).show();
                            }else{
                                updateService(ID, name, role);
                                dialog.dismiss();
                            }
                        }
                    }
                });
        AlertDialog dialogue = builder.create();
        dialogue.show();
    }

    private void updateService(String id, String name, String role) {

        DatabaseReference reference = databaseServices.child(id);

        Service service = new Service(name, role, id);

        reference.setValue(service);

        Toast.makeText(getApplicationContext(), "Service Updated", Toast.LENGTH_LONG).show();
    }

    private void deleteService(String id) {

        DatabaseReference reference = databaseServices.child(id);

        reference.removeValue();

        Toast.makeText(getApplicationContext(), "Service deleted", Toast.LENGTH_LONG).show();

    }


    public void addServiceClickHandler(View target) throws NoSuchAlgorithmException {

        Intent myIntent = new Intent(services_view.this, add_service.class);

        startActivity(myIntent);

    }
    public void backClickHandler(View target) throws NoSuchAlgorithmException {

        Intent myIntent = new Intent(services_view.this, admin_page.class);

        startActivity(myIntent);

    }
    public boolean invalidName(String str) {
        return !str.matches("[a-zA-Z]+");
    }
}
