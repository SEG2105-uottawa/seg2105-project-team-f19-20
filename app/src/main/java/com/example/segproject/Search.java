package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by);
    }

    public void searchHoursClickHandler(View target) {
        Intent myIntent = getIntent();
        myIntent = new Intent(Search.this, SearchHours.class);
        startActivity(myIntent);
    }
    public void searchAddressClickHandler(View target) {
        Intent myIntent = getIntent();
        myIntent = new Intent(Search.this, SearchAddressPage.class);
        startActivity(myIntent);
    }
    public void searchServiceClickHandler(View target) {
        Intent myIntent = getIntent();
        myIntent = new Intent(Search.this, SearchServices.class);
        startActivity(myIntent);
    }
}
