package com.example.krepco;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.krepco.adapters.CalculatorTypeAdapter;
import com.example.krepco.adapters.OperationTypeAdapter;
import com.example.krepco.models.CalculatorType;
import com.example.krepco.models.OperationType;
import com.example.krepco.utils.CalculatorEngine;

public class CalculatorActivity extends AppCompatActivity {

    // Элементы интерфейса
    private Spinner spinnerCalculatorType;
    private Spinner spinnerOperationType;
    private TextView tvExpression;
    private EditText editResult;
    private Button btnBack;

    // Контейнеры с кнопками
    private LinearLayout linearLayoutArithmetic;
    private LinearLayout linearLayoutPowerRoot;
    private LinearLayout linearLayoutLogarithmic;
    private LinearLayout linearLayoutTrigonometric;
    private LinearLayout linearLayoutFinancial;
    private LinearLayout linearLayoutConstruction;

    // Кнопки арифметические (всегда видны)
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button btnDot, btnPlus, btnMinus, btnMultiply, btnDivide, btnEquals;
    private Button btnClear, btnBackspace, btnPlusMinus, btnPercent;

    // Кнопки степени и корни
    private Button btnPower2, btnPower3, btnPowerY, btnSqrt, btnSqrt3;

    // Кнопки логарифмы (только log и ln)
    private Button btnLog, btnLn;

    // Кнопки тригонометрия
    private Button btnSin, btnCos, btnTan, btnDegRad;

    // Движок калькулятора
    private CalculatorEngine engine = new CalculatorEngine();

    // Состояние
    private CalculatorType currentCalculatorType = CalculatorType.ENGINEERING;
    private OperationType currentOperationType = OperationType.ARITHMETIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        initViews();
        setupSpinners();
        setupButtonListeners();

        // Показываем арифметические кнопки по умолчанию
        showAppropriateButtons();
    }

    private void initViews() {
        // Спиннеры
        spinnerCalculatorType = findViewById(R.id.spinnerCalculatorType);
        spinnerOperationType = findViewById(R.id.spinnerOperationType);

        // Дисплей
        tvExpression = findViewById(R.id.tvExpression);
        editResult = findViewById(R.id.editResult);
        editResult.setText("0");
        editResult.setKeyListener(null); // запрещаем ввод с клавиатуры

        // Контейнеры
        linearLayoutArithmetic = findViewById(R.id.linearLayoutArithmetic);
        linearLayoutPowerRoot = findViewById(R.id.linearLayoutPowerRoot);
        linearLayoutLogarithmic = findViewById(R.id.linearLayoutLogarithmic);
        linearLayoutTrigonometric = findViewById(R.id.linearLayoutTrigonometric);
        linearLayoutFinancial = findViewById(R.id.linearLayoutFinancial);
        linearLayoutConstruction = findViewById(R.id.linearLayoutConstruction);

        // Кнопка "На главную"
        btnBack = findViewById(R.id.btnBack);

        // Инициализация всех кнопок (цифры)
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnDot = findViewById(R.id.btnDot);

        // Операции
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnEquals = findViewById(R.id.btnEquals);
        btnClear = findViewById(R.id.btnClear);
        btnBackspace = findViewById(R.id.btnBackspace);
        btnPlusMinus = findViewById(R.id.btnPlusMinus);
        btnPercent = findViewById(R.id.btnPercent);

        // Степени и корни
        btnPower2 = findViewById(R.id.btnPower2);
        btnPower3 = findViewById(R.id.btnPower3);
        btnPowerY = findViewById(R.id.btnPowerY);
        btnSqrt = findViewById(R.id.btnSqrt);
        btnSqrt3 = findViewById(R.id.btnSqrt3);

        // Логарифмы (только log и ln)
        btnLog = findViewById(R.id.btnLog);
        btnLn = findViewById(R.id.btnLn);

        // Тригонометрия
        btnSin = findViewById(R.id.btnSin);
        btnCos = findViewById(R.id.btnCos);
        btnTan = findViewById(R.id.btnTan);
        btnDegRad = findViewById(R.id.btnDegRad);
    }

    private void setupSpinners() {
        CalculatorTypeAdapter typeAdapter = new CalculatorTypeAdapter(this,
                CalculatorType.values());
        spinnerCalculatorType.setAdapter(typeAdapter);

        spinnerCalculatorType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentCalculatorType = CalculatorType.values()[position];

                // Показываем заглушку для финансового и строительного режимов
                if (currentCalculatorType == CalculatorType.FINANCIAL) {
                    showUnderDevelopmentToast("Финансовый калькулятор");
                } else if (currentCalculatorType == CalculatorType.CONSTRUCTION) {
                    showUnderDevelopmentToast("Строительный калькулятор");
                }

                updateOperationTypeSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerCalculatorType.setSelection(0);
    }

    private void updateOperationTypeSpinner() {
        OperationType[] operationTypes = currentCalculatorType.getOperationTypes();
        OperationTypeAdapter operationAdapter = new OperationTypeAdapter(this, operationTypes);
        spinnerOperationType.setAdapter(operationAdapter);

        spinnerOperationType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                OperationType[] types = currentCalculatorType.getOperationTypes();
                currentOperationType = types[position];
                showAppropriateButtons();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerOperationType.setSelection(0);
    }

    private void showAppropriateButtons() {
        // Скрываем все дополнительные контейнеры
        linearLayoutPowerRoot.setVisibility(View.GONE);
        linearLayoutLogarithmic.setVisibility(View.GONE);
        linearLayoutTrigonometric.setVisibility(View.GONE);
        linearLayoutFinancial.setVisibility(View.GONE);
        linearLayoutConstruction.setVisibility(View.GONE);

        // Арифметические кнопки видны всегда
        linearLayoutArithmetic.setVisibility(View.VISIBLE);

        // Показываем дополнительные кнопки в зависимости от типа операции
        if (currentCalculatorType == CalculatorType.ENGINEERING) {
            switch (currentOperationType) {
                case POWER_ROOT:
                    linearLayoutPowerRoot.setVisibility(View.VISIBLE);
                    break;
                case LOGARITHMIC:
                    linearLayoutLogarithmic.setVisibility(View.VISIBLE);
                    break;
                case TRIGONOMETRIC:
                    linearLayoutTrigonometric.setVisibility(View.VISIBLE);
                    updateDegRadButton();
                    break;
            }
        } else if (currentCalculatorType == CalculatorType.FINANCIAL) {
            // Показываем заглушку, так как кнопок нет
            linearLayoutFinancial.setVisibility(View.VISIBLE);
            // Можно добавить TextView с надписью "В разработке"
        } else if (currentCalculatorType == CalculatorType.CONSTRUCTION) {
            // Показываем заглушку, так как кнопок нет
            linearLayoutConstruction.setVisibility(View.VISIBLE);
        }
    }

    private void setupButtonListeners() {
        // Цифры
        View.OnClickListener numberListener = v -> {
            Button btn = (Button) v;
            editResult.setText(engine.appendNumber(btn.getText().toString()));
            updateExpression();
        };

        btn0.setOnClickListener(numberListener);
        btn1.setOnClickListener(numberListener);
        btn2.setOnClickListener(numberListener);
        btn3.setOnClickListener(numberListener);
        btn4.setOnClickListener(numberListener);
        btn5.setOnClickListener(numberListener);
        btn6.setOnClickListener(numberListener);
        btn7.setOnClickListener(numberListener);
        btn8.setOnClickListener(numberListener);
        btn9.setOnClickListener(numberListener);
        btnDot.setOnClickListener(numberListener);

        // Операторы
        btnPlus.setOnClickListener(v -> {
            String expr = engine.appendOperator("+");
            tvExpression.setText(expr);
            editResult.setText("0");
        });

        btnMinus.setOnClickListener(v -> {
            String expr = engine.appendOperator("-");
            tvExpression.setText(expr);
            editResult.setText("0");
        });

        btnMultiply.setOnClickListener(v -> {
            String expr = engine.appendOperator("*");
            tvExpression.setText(expr);
            editResult.setText("0");
        });

        btnDivide.setOnClickListener(v -> {
            String expr = engine.appendOperator("/");
            tvExpression.setText(expr);
            editResult.setText("0");
        });

        btnEquals.setOnClickListener(v -> {
            String result = engine.calculate();
            editResult.setText(result);
            tvExpression.setText("");
        });

        btnClear.setOnClickListener(v -> {
            engine.clear();
            editResult.setText("0");
            tvExpression.setText("");
        });

        btnBackspace.setOnClickListener(v -> {
            engine.backspace();
            editResult.setText(engine.getCurrentInput());
            updateExpression();
        });

        btnPlusMinus.setOnClickListener(v -> {
            engine.toggleSign();
            editResult.setText(engine.getCurrentInput());
        });

        btnPercent.setOnClickListener(v -> {
            showUnderDevelopmentToast("Проценты");
        });

        // Степени и корни
        btnPower2.setOnClickListener(v ->
                editResult.setText(engine.calculateSquare()));

        btnPower3.setOnClickListener(v ->
                editResult.setText(engine.calculateCube()));

        btnPowerY.setOnClickListener(v -> {
            if (!engine.getCurrentInput().equals("0")) {
                showPowerDialog();
            } else {
                Toast.makeText(this, "Введите основание", Toast.LENGTH_SHORT).show();
            }
        });

        btnSqrt.setOnClickListener(v ->
                editResult.setText(engine.calculateSqrt()));

        btnSqrt3.setOnClickListener(v ->
                editResult.setText(engine.calculateCbrt()));

        // Логарифмы
        btnLog.setOnClickListener(v ->
                editResult.setText(engine.calculateLog()));

        btnLn.setOnClickListener(v ->
                editResult.setText(engine.calculateLn()));

        // Тригонометрия
        btnSin.setOnClickListener(v ->
                editResult.setText(engine.calculateSin()));

        btnCos.setOnClickListener(v ->
                editResult.setText(engine.calculateCos()));

        btnTan.setOnClickListener(v ->
                editResult.setText(engine.calculateTan()));

        btnDegRad.setOnClickListener(v -> {
            engine.toggleDegRad();
            updateDegRadButton();
        });

        // Кнопка "На главную"
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(CalculatorActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }

    private void showPowerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Возведение в степень");

        // Создаем layout для диалога
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 20, 50, 20);

        // Показываем основание
        TextView baseText = new TextView(this);
        baseText.setText("Основание: " + engine.getCurrentInput());
        baseText.setTextColor(getResources().getColor(R.color.white));
        baseText.setTextSize(16);
        baseText.setPadding(0, 0, 0, 10);
        layout.addView(baseText);

        // Поле для ввода степени
        TextView expLabel = new TextView(this);
        expLabel.setText("Степень:");
        expLabel.setTextColor(getResources().getColor(R.color.white));
        expLabel.setTextSize(14);
        expLabel.setPadding(0, 10, 0, 5);
        layout.addView(expLabel);

        EditText exponentInput = new EditText(this);
        exponentInput.setHint("Введите степень");
        exponentInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        exponentInput.setBackgroundColor(getResources().getColor(R.color.gray));
        exponentInput.setTextColor(getResources().getColor(R.color.white));
        exponentInput.setHintTextColor(getResources().getColor(R.color.white));
        exponentInput.setPadding(20, 10, 20, 10);
        layout.addView(exponentInput);

        builder.setView(layout);

        // Кнопки
        builder.setPositiveButton("Вычислить", (dialog, which) -> {
            String expStr = exponentInput.getText().toString();
            if (!expStr.isEmpty()) {
                try {
                    double base = Double.parseDouble(engine.getCurrentInput());
                    double exp = Double.parseDouble(expStr);
                    String result = engine.calculateBinaryOperation(base, exp, "x^y");
                    editResult.setText(result);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Ошибка ввода", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Введите степень", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Отмена", null);

        // Показываем диалог
        AlertDialog dialog = builder.create();
        dialog.show();

        // Настраиваем цвета кнопок
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.orange));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.white));
    }

    private void showUnderDevelopmentToast(String feature) {
        Toast.makeText(this, feature + " в разработке", Toast.LENGTH_SHORT).show();
    }

    private void updateExpression() {
        String expr = engine.getExpression();
        tvExpression.setText(expr);
    }

    private void updateDegRadButton() {
        if (engine.isDegrees()) {
            btnDegRad.setText("DEG");
            btnDegRad.setBackgroundTintList(getResources().getColorStateList(R.color.orange));
        } else {
            btnDegRad.setText("RAD");
            btnDegRad.setBackgroundTintList(getResources().getColorStateList(R.color.gray));
        }
    }
}