package com.example.krepco.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.krepco.R;
import com.example.krepco.models.CalculatorType;

public class CalculatorTypeAdapter extends ArrayAdapter<CalculatorType> {

    public CalculatorTypeAdapter(Context context, CalculatorType[] types) {
        super(context, R.layout.spinner_item, types);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.spinner_text);
        CalculatorType type = getItem(position);

        if (type != null) {
            textView.setText(type.getDisplayName());
        }

        return convertView;
    }
}