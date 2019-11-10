package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class service_item extends ArrayAdapter<Service> {

    public service_item(Activity context, List<Service> services) {
        super(context, R.layout.activity_service_item, services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Service service = getItem(position);

        View item = LayoutInflater.from(getContext()).inflate(R.layout.activity_service_item, null, true);

        TextView name = (TextView) item.findViewById(R.id.nameView);
        TextView role = (TextView) item.findViewById(R.id.roleView);

        name.setText(service.getName());
        role.setText(service.getRole());

        return item;
    }

}

