package com.example.krepco;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.krepco.adapters.UnitSpinnerAdapter;
import com.example.krepco.data.UnitDataProvider;
import com.example.krepco.models.Unit;
import com.example.krepco.models.UnitCategory;
import com.example.krepco.models.UnitSubcategory;
import com.example.krepco.utils.UnitConverter;
import java.util.List;

public class ConverterActivity extends AppCompatActivity {

    private Button btnCtg1, btnCtg2, btnCtg3, btnCtg4, btnCtg5, btnCtg6;
    private Button btnMtrc1, btnMtrc2, btnMtrc3, btnMtrc4;
    private LinearLayout linearLayoutMtrcGr;
    private EditText editTxt1, editTxt2;
    private Spinner spinner1, spinner2;
    private LinearLayout linearLayoutRslt;
    private Button btnBack;

    // Состояние
    private UnitCategory selectedCategory = null;
    private UnitSubcategory selectedSubcategory = null;
    private Unit[] currentUnits = new Unit[0];

    // Флаг для предотвращения зацикливания при обновлении
    private boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        initViews();
        setupListeners();

        linearLayoutMtrcGr.setVisibility(View.GONE);
        linearLayoutRslt.setVisibility(View.GONE);
    }

    private void initViews() {

        btnCtg1 = findViewById(R.id.btnCtg1);
        btnCtg2 = findViewById(R.id.btnCtg2);
        btnCtg3 = findViewById(R.id.btnCtg3);
        btnCtg4 = findViewById(R.id.btnCtg4);
        btnCtg5 = findViewById(R.id.btnCtg5);
        btnCtg6 = findViewById(R.id.btnCtg6);

        btnMtrc1 = findViewById(R.id.btnMtrc1);
        btnMtrc2 = findViewById(R.id.btnMtrc2);
        btnMtrc3 = findViewById(R.id.btnMtrc3);
        btnMtrc4 = findViewById(R.id.btnMtrc4);

        linearLayoutMtrcGr = findViewById(R.id.linearLayoutMtrcGr);

        editTxt1 = findViewById(R.id.editTxt1);
        editTxt2 = findViewById(R.id.editTxt2);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        linearLayoutRslt = findViewById(R.id.linearLayoutRslt);

        btnBack = findViewById(R.id.btnBack);

        btnCtg1.setText(UnitCategory.GEOMETRIC.getDisplayName());
        btnCtg2.setText(UnitCategory.MECHANICAL.getDisplayName());
        btnCtg3.setText(UnitCategory.TIME.getDisplayName());
        btnCtg4.setText(UnitCategory.ENERGY.getDisplayName());
        btnCtg5.setText(UnitCategory.CONSTRUCTION.getDisplayName());
        btnCtg6.setText(UnitCategory.ECONOMIC.getDisplayName());
    }

    private void setupListeners() {
        btnCtg1.setOnClickListener(v -> selectCategory(UnitCategory.GEOMETRIC, btnCtg1));
        btnCtg2.setOnClickListener(v -> selectCategory(UnitCategory.MECHANICAL, btnCtg2));
        btnCtg3.setOnClickListener(v -> selectCategory(UnitCategory.TIME, btnCtg3));
        btnCtg4.setOnClickListener(v -> selectCategory(UnitCategory.ENERGY, btnCtg4));
        btnCtg5.setOnClickListener(v -> selectCategory(UnitCategory.CONSTRUCTION, btnCtg5));
        btnCtg6.setOnClickListener(v -> selectCategory(UnitCategory.ECONOMIC, btnCtg6));

        btnMtrc1.setOnClickListener(v -> selectSubcategory(0, btnMtrc1));
        btnMtrc2.setOnClickListener(v -> selectSubcategory(1, btnMtrc2));
        btnMtrc3.setOnClickListener(v -> selectSubcategory(2, btnMtrc3));
        btnMtrc4.setOnClickListener(v -> selectSubcategory(3, btnMtrc4));

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isUpdating && selectedSubcategory != null) {
                    performConversion();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isUpdating && selectedSubcategory != null) {
                    performConversion();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        editTxt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!isUpdating && selectedSubcategory != null) {
                    isUpdating = true;
                    performConversionFromFirst();
                    isUpdating = false;
                }
            }
        });

        editTxt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!isUpdating && selectedSubcategory != null) {
                    isUpdating = true;
                    performConversionFromSecond();
                    isUpdating = false;
                }
            }
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(ConverterActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }

    private void selectCategory(UnitCategory category, Button selectedButton) {
        // Сброс
        resetCategoryButtons();

        selectedButton.setBackgroundColor(getResources().getColor(R.color.orange));
        selectedCategory = category;
        selectedSubcategory = null;
        resetSubcategoryButtons();
        showSubcategories(category);
        linearLayoutRslt.setVisibility(View.GONE);
    }

    private void showSubcategories(UnitCategory category) {
        UnitSubcategory[] subcategories = category.getSubcategories();

        if (subcategories.length >= 4) {
            btnMtrc1.setText(subcategories[0].getDisplayName());
            btnMtrc2.setText(subcategories[1].getDisplayName());
            btnMtrc3.setText(subcategories[2].getDisplayName());
            btnMtrc4.setText(subcategories[3].getDisplayName());

            linearLayoutMtrcGr.setVisibility(View.VISIBLE);
        }
    }

    private void selectSubcategory(int index, Button selectedButton) {
        if (selectedCategory == null) return;
        // Сброс
        resetSubcategoryButtons();
        selectedButton.setBackgroundColor(getResources().getColor(R.color.orange));
        UnitSubcategory[] subcategories = selectedCategory.getSubcategories();
        if (index < subcategories.length) {
            selectedSubcategory = subcategories[index];
            loadUnitsForSubcategory(selectedSubcategory);
            linearLayoutRslt.setVisibility(View.VISIBLE);
        }
    }

private void loadUnitsForSubcategory(UnitSubcategory subcategory) {
    List<Unit> units = UnitDataProvider.getInstance().getUnitsForSubcategory(subcategory);
    currentUnits = units.toArray(new Unit[0]);

    if (currentUnits.length > 0) {
        isUpdating = true;

        UnitSpinnerAdapter adapter1 = new UnitSpinnerAdapter(this, units);
        UnitSpinnerAdapter adapter2 = new UnitSpinnerAdapter(this, units);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        if (currentUnits.length > 1) {
            spinner1.setSelection(0);
            spinner2.setSelection(1);
        } else {
            spinner1.setSelection(0);
            spinner2.setSelection(0);
        }

        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();

        isUpdating = false;
    }
}

    private void performConversionFromFirst() {
        if (selectedSubcategory == null || currentUnits.length == 0) return;

        String text = editTxt1.getText().toString();
        if (text.isEmpty()) {
            editTxt2.setText("");
            return;
        }

        try {
            double value = Double.parseDouble(text);
            Unit fromUnit = (Unit) spinner1.getSelectedItem();
            Unit toUnit = (Unit) spinner2.getSelectedItem();

            if (fromUnit != null && toUnit != null) {
                double result = UnitConverter.convert(value, fromUnit, toUnit);
                editTxt2.setText(UnitConverter.formatResult(result));
            }
        } catch (NumberFormatException e) {
            editTxt2.setText("");
        }
    }

    private void performConversionFromSecond() {
        if (selectedSubcategory == null || currentUnits.length == 0) return;

        String text = editTxt2.getText().toString();
        if (text.isEmpty()) {
            editTxt1.setText("");
            return;
        }

        try {
            double value = Double.parseDouble(text);
            Unit fromUnit = (Unit) spinner2.getSelectedItem();
            Unit toUnit = (Unit) spinner1.getSelectedItem();

            if (fromUnit != null && toUnit != null) {
                double result = UnitConverter.convert(value, fromUnit, toUnit);
                editTxt1.setText(UnitConverter.formatResult(result));
            }
        } catch (NumberFormatException e) {
            editTxt1.setText("");
        }
    }

    private void performConversion() {
        if (selectedSubcategory == null || currentUnits.length == 0) return;

        String text = editTxt1.getText().toString();
        if (!text.isEmpty()) {
            try {
                double value = Double.parseDouble(text);
                Unit fromUnit = (Unit) spinner1.getSelectedItem();
                Unit toUnit = (Unit) spinner2.getSelectedItem();

                if (fromUnit != null && toUnit != null) {
                    double result = UnitConverter.convert(value, fromUnit, toUnit);
                    isUpdating = true;
                    editTxt2.setText(UnitConverter.formatResult(result));
                    isUpdating = false;
                }
            } catch (NumberFormatException e) {
                // Игнорирование
            }
        }
    }

    private void resetCategoryButtons() {
        int defaultColor = getResources().getColor(R.color.gray);
        btnCtg1.setBackgroundColor(defaultColor);
        btnCtg2.setBackgroundColor(defaultColor);
        btnCtg3.setBackgroundColor(defaultColor);
        btnCtg4.setBackgroundColor(defaultColor);
        btnCtg5.setBackgroundColor(defaultColor);
        btnCtg6.setBackgroundColor(defaultColor);
    }

    private void resetSubcategoryButtons() {
        int defaultColor = getResources().getColor(R.color.gray);
        btnMtrc1.setBackgroundColor(defaultColor);
        btnMtrc2.setBackgroundColor(defaultColor);
        btnMtrc3.setBackgroundColor(defaultColor);
        btnMtrc4.setBackgroundColor(defaultColor);
    }
}