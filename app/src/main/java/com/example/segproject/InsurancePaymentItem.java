package com.example.segproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class InsurancePaymentItem extends ArrayAdapter<String> {

    public InsurancePaymentItem(Activity context, List<String> insurancePaymentArray) {
        super(context, R.layout.insurance_payment_item, insurancePaymentArray);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String insurancePaymentItem = getItem(position);

        View item = LayoutInflater.from(getContext()).inflate(R.layout.insurance_payment_item, null, true);

        TextView name = (TextView) item.findViewById(R.id.insurancePaymentNameView);

        name.setText(insurancePaymentItem);

        return item;
    }
}
