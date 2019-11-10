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

public class user_item extends ArrayAdapter<Account> {

    public user_item(Activity context, List<Account> accounts) {
        super(context, R.layout.activity_user_item, accounts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Account account = getItem(position);

        View item = LayoutInflater.from(getContext()).inflate(R.layout.activity_user_item, null, true);

        TextView userName = item.findViewById(R.id.userName);
        TextView userRole = item.findViewById(R.id.userRole);

        userName.setText(account.getFirstName());
        userRole.setText(account.getRole());

        return item;
    }

}

