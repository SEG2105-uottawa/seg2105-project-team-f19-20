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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeInsurancePayment extends AppCompatActivity {

    EditText insuranceProviderInput;
    EditText paymentMethodInput;

    String id;

    ListView listViewInsuranceProvider;
    ListView listViewPaymentMethod;

    List<String> insuranceProvidersArray;
    List<String> paymentMethodsArray;

    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_insurance_payment);

        databaseUsers = FirebaseDatabase.getInstance().getReference().child("users");

        insuranceProviderInput = (EditText) findViewById(R.id.insuranceProviderInput);
        paymentMethodInput = (EditText) findViewById(R.id.paymentMethodInput);

        Intent myIntent = getIntent();
        id = myIntent.getStringExtra("userID");

        listViewInsuranceProvider = findViewById(R.id.listInsuranceProvider);
        listViewPaymentMethod = findViewById(R.id.listPaymentMethod);

        insuranceProvidersArray = new ArrayList<>();
        paymentMethodsArray = new ArrayList<>();

        //dialogue stuff
        listViewInsuranceProvider.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int a, long b) {
                String insuranceProviderItem = insuranceProvidersArray.get(a);
                dialogueBoxInsuranceProvider(insuranceProviderItem);
            }
        });

        listViewPaymentMethod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int x, long y) {
                String paymentMethodItem = paymentMethodsArray.get(x);
                dialogueBoxPaymentMethod(paymentMethodItem);
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

                insuranceProvidersArray.clear();
                paymentMethodsArray.clear();

                for(DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("userID").getValue().equals(id)) {
                        //it exists
                        if(data.child("insuranceProviders") != null) {
                            for(DataSnapshot elem : data.child("insuranceProviders").getChildren()) {
                                insuranceProvidersArray.add((String) elem.getValue());
                            }
                        }
                        if(data.child("paymentMethods") != null) {
                            for(DataSnapshot elem : data.child("paymentMethods").getChildren()) {
                                paymentMethodsArray.add((String) elem.getValue());
                            }
                        }
                    }
                }

                InsurancePaymentItem insuranceProviderAdapter = new InsurancePaymentItem(EmployeeInsurancePayment.this, insuranceProvidersArray);
                listViewInsuranceProvider.setAdapter(insuranceProviderAdapter);

                InsurancePaymentItem paymentMethodAdapter = new InsurancePaymentItem(EmployeeInsurancePayment.this, paymentMethodsArray);
                listViewPaymentMethod.setAdapter(paymentMethodAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }

    private void dialogueBoxInsuranceProvider(final String name){

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

                        deleteInsuranceProvider(name);
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

    private void dialogueBoxPaymentMethod(final String name){

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

                        deletePaymentMethod(name);
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

    public void backClickHandler(View target) throws NoSuchAlgorithmException {
        Intent myIntent = new Intent(EmployeeInsurancePayment.this, EmployeePage.class);
        startActivity(myIntent);
    }

    public void addInsuranceProviderClickHandler(View target) {
        if(invalidName(insuranceProviderInput.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter a valid insurance provider.", Toast.LENGTH_LONG).show();
        } else {
            insuranceProvidersArray.add(insuranceProviderInput.getText().toString().trim());
            databaseUsers.child(id).child("insuranceProviders").setValue(insuranceProvidersArray);
            Toast.makeText(getApplicationContext(), "Insurance provider added", Toast.LENGTH_LONG).show();
        }
    }

    private void deleteInsuranceProvider(String name) {
        insuranceProvidersArray.remove(name);
        databaseUsers.child(id).child("insuranceProviders").setValue(insuranceProvidersArray);
        Toast.makeText(getApplicationContext(), "Insurance provider deleted", Toast.LENGTH_LONG).show();
    }

    public void addPaymentMethodClickHandler(View target) {
        if(invalidName(paymentMethodInput.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter a valid payment method.", Toast.LENGTH_LONG).show();
        } else {
            paymentMethodsArray.add(paymentMethodInput.getText().toString().trim());
            databaseUsers.child(id).child("paymentMethods").setValue(paymentMethodsArray);
            Toast.makeText(getApplicationContext(), "Payment method added", Toast.LENGTH_LONG).show();
        }
    }

    private void deletePaymentMethod(String name) {
        paymentMethodsArray.remove(name);
        databaseUsers.child(id).child("paymentMethods").setValue(paymentMethodsArray);
        Toast.makeText(getApplicationContext(), "Payment method deleted", Toast.LENGTH_LONG).show();
    }

    public boolean invalidName(String str) {
        //Retrieved regex expression from StackOverFlow
        //Question title: Java Regex to Validate Full Name allow only Spaces and Letters

        return !str.matches("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}");
    }

}
