package com.example.krepco.models;

public enum UnitSubcategory {
    // Геометрические
    LENGTH("Длина"),
    AREA("Площадь"),
    VOLUME("Объем"),
    ANGLE("Углы"),

    // Механические
    LINEAR_SPEED("Скорость линейная"),
    ANGULAR_SPEED("Скорость угловая"),
    MASS("Масса"),
    PRESSURE("Давление"),

    // Временные
    CLASSIC_DURATION("Длительность классические"),
    PRECISE_DURATION("Длительность высокоточные"),
    FREQUENCY("Частота (СИ)"),
    INTERVALS("Интервалы"),

    // Энергетические
    MECHANICAL_ENERGY("Механическая энергия"),
    THERMAL_ENERGY("Тепловая энергия"),
    ELECTRICAL_ENERGY("Электрическая энергия"),
    POWER("Мощность"),

    // Строительные
    CONSTRUCTION_LOAD("Конструкционная нагрузка"),
    CONSTRUCTION_SLOPE("Конструкционный уклон"),
    MATERIAL_STRENGTH("Сопротивление материалов"),
    VOLUME_WEIGHT("Объемный вес"),

    // Экономические
    CURRENCY("Валюта"),
    SALARY("Зарплата"),
    INTEREST_RATE("Процентная ставка"),
    WEIGHT_COST("Весовая себестоимость");

    private final String displayName;

    UnitSubcategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
