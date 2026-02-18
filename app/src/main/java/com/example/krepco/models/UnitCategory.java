package com.example.krepco.models;
public enum UnitCategory {
    GEOMETRIC("Геометрические"),
    MECHANICAL("Механические"),
    TIME("Временные"),
    ENERGY("Энергетические"),
    CONSTRUCTION("Строительные"),
    ECONOMIC("Экономические");

    private final String displayName;

    UnitCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UnitSubcategory[] getSubcategories() {
        switch (this) {
            case GEOMETRIC:
                return new UnitSubcategory[]{
                        UnitSubcategory.LENGTH,
                        UnitSubcategory.AREA,
                        UnitSubcategory.VOLUME,
                        UnitSubcategory.ANGLE
                };
            case MECHANICAL:
                return new UnitSubcategory[]{
                        UnitSubcategory.LINEAR_SPEED,
                        UnitSubcategory.ANGULAR_SPEED,
                        UnitSubcategory.MASS,
                        UnitSubcategory.PRESSURE
                };
            case TIME:
                return new UnitSubcategory[]{
                        UnitSubcategory.CLASSIC_DURATION,
                        UnitSubcategory.PRECISE_DURATION,
                        UnitSubcategory.FREQUENCY,
                        UnitSubcategory.INTERVALS
                };
            case ENERGY:
                return new UnitSubcategory[]{
                        UnitSubcategory.MECHANICAL_ENERGY,
                        UnitSubcategory.THERMAL_ENERGY,
                        UnitSubcategory.ELECTRICAL_ENERGY,
                        UnitSubcategory.POWER
                };
            case CONSTRUCTION:
                return new UnitSubcategory[]{
                        UnitSubcategory.CONSTRUCTION_LOAD,
                        UnitSubcategory.CONSTRUCTION_SLOPE,
                        UnitSubcategory.MATERIAL_STRENGTH,
                        UnitSubcategory.VOLUME_WEIGHT
                };
            case ECONOMIC:
                return new UnitSubcategory[]{
                        UnitSubcategory.CURRENCY,
                        UnitSubcategory.SALARY,
                        UnitSubcategory.INTEREST_RATE,
                        UnitSubcategory.WEIGHT_COST
                };
            default:
                return new UnitSubcategory[0];
        }
    }
}
