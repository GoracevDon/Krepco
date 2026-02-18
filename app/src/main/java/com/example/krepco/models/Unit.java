package com.example.krepco.models;

public class Unit {
    private final String name;
    private final double toBaseCoefficient;
    private final double fromBaseCoefficient;
    private final UnitSubcategory subcategory;

    public Unit(String name, double toBaseCoefficient, UnitSubcategory subcategory) {
        this.name = name;
        this.toBaseCoefficient = toBaseCoefficient;
        this.fromBaseCoefficient = 1.0 / toBaseCoefficient;
        this.subcategory = subcategory;
    }

    public String getName() {
        return name;
    }

    public double getToBaseCoefficient() {
        return toBaseCoefficient;
    }

    public double getFromBaseCoefficient() {
        return fromBaseCoefficient;
    }

    public UnitSubcategory getSubcategory() {
        return subcategory;
    }

    @Override
    public String toString() {
        return name;
    }
}