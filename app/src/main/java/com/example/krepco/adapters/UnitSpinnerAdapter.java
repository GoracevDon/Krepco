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
import com.example.krepco.models.Unit;
import java.util.List;

public class UnitSpinnerAdapter extends ArrayAdapter<Unit> {

    public UnitSpinnerAdapter(Context context, List<Unit> units) {
        super(context, R.layout.spinner_item, units);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Принудительно
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);

        TextView textView = convertView.findViewById(R.id.spinner_text);
        Unit unit = getItem(position);

        if (unit != null) {
            textView.setText(unit.getName());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Для выпадающего списка тот же layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.spinner_text);
        Unit unit = getItem(position);

        if (unit != null) {
            textView.setText(unit.getName());
        }

        return convertView;
    }
}