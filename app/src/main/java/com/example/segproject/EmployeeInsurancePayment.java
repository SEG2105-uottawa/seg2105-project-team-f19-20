package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EmployeeInsurancePayment extends AppCompatActivity {

    DatabaseReference databaseUsers;
    TableLayout t1, t2;
//    EditText insuranceProviderInput;
//    EditText paymentMethodInput;

//    Intent myIntent = getIntent();
//    final String id = myIntent.getStringExtra("userID");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_insurance_payment);

        databaseUsers = FirebaseDatabase.getInstance().getReference().child("users");

        t1 = (TableLayout) findViewById(R.id.table1);
        t2 = (TableLayout) findViewById(R.id.table2);

//        insuranceProviderInput = (EditText) findViewById(R.id.insuranceProviderInput);
//        paymentMethodInput = (EditText) findViewById(R.id.paymentMethodInput);

//        databaseUsers.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot data : dataSnapshot.getChildren()) {
//                    if (data.child("userID").getValue().equals(id)) {
//                        //it exists
//                        if(data.child("insuranceProviders") != null) {
//                            for(DataSnapshot elem : data.child("insuranceProviders").getChildren()) {
//                                displayInsuranceProvider((String) elem.getValue());
//                            }
//                        }
//                        if(data.child("paymentMethods") != null) {
//                            for(DataSnapshot elem : data.child("paymentMethods").getChildren()) {
//                                displayInsuranceProvider((String) elem.getValue());
//                            }
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    public void addInsuranceProviderClickHandler(View target) {
//        databaseUsers.child(id).child("insuranceProviders").setValue(insuranceProviderInput.getText().toString().trim());
//        displayInsuranceProvider(insuranceProviderInput.getText().toString().trim());
        displayInsuranceProvider("something");
    }

    public void addPaymentMethodClickHandler(View target) {
//        databaseUsers.child(id).child("paymentMethods").setValue(paymentMethodInput.getText().toString().trim());
//        displayPaymentMethod(paymentMethodInput.getText().toString().trim());
        displayPaymentMethod("something");
    }

    public void displayInsuranceProvider(String name) {
        TableRow tr = new TableRow(this);
        TextView textView = new TextView(this);
        textView.setText(name);
        textView.setTextSize(15);
        textView.setGravity(Gravity.CENTER);
        tr.addView(textView);
        t1.addView(tr);
    }

    public void displayPaymentMethod(String name) {
        TableRow tr = new TableRow(this);
        TextView textView = new TextView(this);
        textView.setText(name);
        textView.setTextSize(15);
        textView.setGravity(Gravity.CENTER);
        tr.addView(textView);
        t2.addView(tr);
    }

}
