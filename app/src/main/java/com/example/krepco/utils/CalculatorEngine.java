package com.example.krepco.utils;

import java.util.Stack;

public class CalculatorEngine {
    private StringBuilder currentInput = new StringBuilder();
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String lastOperation = "";
    private boolean isNewNumber = true;
    private boolean isDegrees = true;

    // ВВОД ЧИСЕЛ

    public String appendNumber(String number) {
        if (isNewNumber) {
            currentInput = new StringBuilder();
            isNewNumber = false;
        }

        if (number.equals(".")) {
            if (!currentInput.toString().contains(".")) {
                if (currentInput.length() == 0) {
                    currentInput.append("0");
                }
                currentInput.append(".");
            }
        } else {
            currentInput.append(number);
        }

        // Если операция уже выбрана, то вводим второе число
        if (!lastOperation.isEmpty()) {
            secondNumber = getCurrentValue();
        }

        return currentInput.toString();
    }

    public String appendOperator(String operator) {
        if (currentInput.length() > 0) {
            if (firstNumber != 0 && !lastOperation.isEmpty()) {
                calculate();
            }

            firstNumber = getCurrentValue();
            lastOperation = operator;
            isNewNumber = true;

            return formatNumber(firstNumber) + " " + operator;
        }
        return "";
    }

    public String calculate() {
        if (lastOperation.isEmpty()) {
            return currentInput.toString();
        }

        if (isNewNumber) {
            secondNumber = firstNumber;
        }

        double result = 0;

        switch (lastOperation) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber == 0) {
                    return "Ошибка";
                }
                result = firstNumber / secondNumber;
                break;
            default:
                return currentInput.toString();
        }

        firstNumber = result;
        secondNumber = 0;
        lastOperation = "";
        currentInput = new StringBuilder(formatNumber(result));
        isNewNumber = true;

        return formatNumber(result);
    }


    public String calculateSquare() {
        double value = getCurrentValue();
        double result = value * value;
        return processUnaryOperation(result);
    }

    public String calculateCube() {
        double value = getCurrentValue();
        double result = value * value * value;
        return processUnaryOperation(result);
    }

    public String calculatePower(double exponent) {
        double value = getCurrentValue();
        double result = Math.pow(value, exponent);
        return processUnaryOperation(result);
    }

    public String calculateSqrt() {
        double value = getCurrentValue();
        if (value < 0) return "Ошибка";
        double result = Math.sqrt(value);
        return processUnaryOperation(result);
    }

    public String calculateCbrt() {
        double value = getCurrentValue();
        double result = Math.cbrt(value);
        return processUnaryOperation(result);
    }

    public String calculateLog() {
        double value = getCurrentValue();
        if (value <= 0) return "Ошибка";
        double result = Math.log10(value);
        return processUnaryOperation(result);
    }

    public String calculateLn() {
        double value = getCurrentValue();
        if (value <= 0) return "Ошибка";
        double result = Math.log(value);
        return processUnaryOperation(result);
    }

    public String calculateSin() {
        double value = getCurrentValue();
        double radians = isDegrees ? Math.toRadians(value) : value;
        double result = Math.sin(radians);
        return processUnaryOperation(result);
    }

    public String calculateCos() {
        double value = getCurrentValue();
        double radians = isDegrees ? Math.toRadians(value) : value;
        double result = Math.cos(radians);
        return processUnaryOperation(result);
    }

    public String calculateTan() {
        double value = getCurrentValue();
        double radians = isDegrees ? Math.toRadians(value) : value;
        double result = Math.tan(radians);
        return processUnaryOperation(result);
    }

    public String calculateBinaryOperation(double first, double second, String operation) {
        double result = 0;

        switch (operation) {
            case "x^y":
            case "power":
                result = Math.pow(first, second);
                break;
            case "y√x":
                if (second == 0) return "Ошибка";
                result = Math.pow(first, 1.0 / second);
                break;
            case "log_a":
                if (first <= 0 || second <= 0 || second == 1) return "Ошибка";
                result = Math.log(first) / Math.log(second);
                break;
            default:
                return "Ошибка";
        }

        currentInput = new StringBuilder(formatNumber(result));
        firstNumber = result;
        secondNumber = 0;
        isNewNumber = true;

        return currentInput.toString();
    }

    private String processUnaryOperation(double result) {
        currentInput = new StringBuilder(formatNumber(result));
        // Обновляем текущее число в зависимости от контекста
        if (lastOperation.isEmpty()) {
            firstNumber = result;
        } else {
            secondNumber = result;
        }
        isNewNumber = true;
        return currentInput.toString();
    }

    // ВСПОМОГАТЕЛЬНЫЕ

    public void clear() {
        currentInput = new StringBuilder();
        firstNumber = 0;
        secondNumber = 0;
        lastOperation = "";
        isNewNumber = true;
    }

    public void backspace() {
        if (currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
            if (currentInput.length() == 0) {
                currentInput.append("0");
            }

            if (lastOperation.isEmpty()) {
                firstNumber = getCurrentValue();
            } else {
                secondNumber = getCurrentValue();
            }
        }
    }

    public void toggleSign() {
        double value = getCurrentValue();
        value = -value;
        currentInput = new StringBuilder(formatNumber(value));

        if (lastOperation.isEmpty()) {
            firstNumber = value;
        } else {
            secondNumber = value;
        }
    }

    public void toggleDegRad() {
        isDegrees = !isDegrees;
    }

    public boolean isDegrees() {
        return isDegrees;
    }

    private double getCurrentValue() {
        try {
            return Double.parseDouble(currentInput.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getCurrentInput() {
        if (currentInput.length() == 0) {
            return "0";
        }
        return currentInput.toString();
    }

    public String getExpression() {
        if (firstNumber != 0 && !lastOperation.isEmpty()) {
            return formatNumber(firstNumber) + " " + lastOperation;
        }
        return "";
    }

    private String formatNumber(double value) {
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            return "Ошибка";
        }

        if (value == (long) value) {
            return String.valueOf((long) value);
        }

        return String.format("%.4f", value).replaceAll("0*$", "").replaceAll("\\.$", "");
    }
}