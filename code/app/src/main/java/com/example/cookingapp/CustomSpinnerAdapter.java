package com.example.cookingapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    public CustomSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        // don't display empty selection (last item)
        if (position == getCount()) {
            ((TextView)view.findViewById(android.R.id.text1)).setText("");
            ((TextView)view.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
        }
        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        view.setPadding(0, 0, 0, 0);
        return view;
    }

    @Override
    public int getCount() {
        return super.getCount()-1;
    }
}
