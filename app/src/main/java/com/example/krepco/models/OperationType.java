package com.example.krepco.models;

public enum OperationType {
    // Инженерные
    ARITHMETIC("Арифметические"),
    POWER_ROOT("Степени и корни"),
    LOGARITHMIC("Логарифмические"),
    TRIGONOMETRIC("Тригонометрические"),

    // Финансовые
    BUDGETING("Бюджетирование"),
    COMMISSION("Комиссии"),
    CURRENCY("Валютные операции"),

    // Строительные
    MATERIALS("Расчет материалов"),
    STRUCTURAL("Конструктивные расчеты");

    private final String displayName;

    OperationType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}