package com.example.krepco.models;

public enum CalculatorType {
    ENGINEERING("Инженерный"),
    FINANCIAL("Финансовый"),
    CONSTRUCTION("Строительный");

    private final String displayName;

    CalculatorType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public OperationType[] getOperationTypes() {
        switch (this) {
            case ENGINEERING:
                return new OperationType[]{
                        OperationType.ARITHMETIC,
                        OperationType.POWER_ROOT,
                        OperationType.LOGARITHMIC,
                        OperationType.TRIGONOMETRIC
                };
            case FINANCIAL:
                return new OperationType[]{
                        OperationType.BUDGETING,
                        OperationType.COMMISSION,
                        OperationType.CURRENCY
                };
            case CONSTRUCTION:
                return new OperationType[]{
                        OperationType.MATERIALS,
                        OperationType.STRUCTURAL
                };
            default:
                return new OperationType[0];
        }
    }
}