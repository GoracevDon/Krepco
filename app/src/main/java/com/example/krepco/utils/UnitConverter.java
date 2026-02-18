package com.example.krepco.utils;

import com.example.krepco.models.Unit;
import com.example.krepco.models.UnitSubcategory;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class UnitConverter {
    private static final DecimalFormat df = new DecimalFormat("#.######",
            DecimalFormatSymbols.getInstance(Locale.US));

    /**
     * Конвертировать знвчение из одной единицы в другцю
     */
    public static double convert(double value, Unit fromUnit, Unit toUnit) {
        if (fromUnit == null || toUnit == null) {
            return 0;
        }

        // Особая логика
        if (fromUnit.getSubcategory() == UnitSubcategory.CONSTRUCTION_SLOPE) {
            return convertSlope(value, fromUnit, toUnit);
        }

        // Особая логика
        if (fromUnit.getSubcategory() == UnitSubcategory.ANGLE) {
            return convertAngle(value, fromUnit, toUnit);
        }

        // Особая логика
        double valueInBase = value * fromUnit.getToBaseCoefficient();
        return valueInBase * toUnit.getFromBaseCoefficient();
    }

    /**
     * Конвертация для углов
     */
    private static double convertAngle(double value, Unit fromUnit, Unit toUnit) {
        // Градус как базовая единица
        double valueInDegrees;

        if (fromUnit.getName().equals("Радиан")) {
            valueInDegrees = Math.toDegrees(value);
        } else if (fromUnit.getName().equals("Миллирадиан")) {
            valueInDegrees = Math.toDegrees(value / 1000.0);
        } else if (fromUnit.getName().equals("Град")) {
            valueInDegrees = value * 0.9;
        } else if (fromUnit.getName().equals("Сантиград")) {
            valueInDegrees = value * 0.009;
        } else if (fromUnit.getName().equals("Оборот")) {
            valueInDegrees = value * 360.0;
        } else if (fromUnit.getName().equals("Полуоборот")) {
            valueInDegrees = value * 180.0;
        } else if (fromUnit.getName().equals("Минута")) {
            valueInDegrees = value / 60.0;
        } else {
            valueInDegrees = value; // Градусы
        }

        // Конвертация из градусов в целевую единицу
        if (toUnit.getName().equals("Радиан")) {
            return Math.toRadians(valueInDegrees);
        } else if (toUnit.getName().equals("Миллирадиан")) {
            return Math.toRadians(valueInDegrees) * 1000.0;
        } else if (toUnit.getName().equals("Град")) {
            return valueInDegrees / 0.9;
        } else if (toUnit.getName().equals("Сантиград")) {
            return valueInDegrees / 0.009;
        } else if (toUnit.getName().equals("Оборот")) {
            return valueInDegrees / 360.0;
        } else if (toUnit.getName().equals("Полуоборот")) {
            return valueInDegrees / 180.0;
        } else if (toUnit.getName().equals("Минута")) {
            return valueInDegrees * 60.0;
        } else {
            return valueInDegrees; // Градусы
        }
    }

    /**
     * Конвертация для уклона
     */
    private static double convertSlope(double value, Unit fromUnit, Unit toUnit) {
        // Процент это базовая единица для уклона
        double valueInPercent;

        if (fromUnit.getName().equals("Градус")) {
            valueInPercent = Math.tan(Math.toRadians(value)) * 100;
        } else if (fromUnit.getName().equals("Сантиметр на метр")) {
            valueInPercent = value;
        } else if (fromUnit.getName().equals("Миллиметр на метр")) {
            valueInPercent = value / 10.0;
        } else if (fromUnit.getName().equals("Промилле")) {
            valueInPercent = value / 10.0;
        } else {
            valueInPercent = value; // Процент
        }

        // Конвертация из процентов в целевую единицу
        if (toUnit.getName().equals("Градус")) {
            return Math.toDegrees(Math.atan(valueInPercent / 100));
        } else if (toUnit.getName().equals("Сантиметр на метр")) {
            return valueInPercent;
        } else if (toUnit.getName().equals("Миллиметр на метр")) {
            return valueInPercent * 10.0;
        } else if (toUnit.getName().equals("Промилле")) {
            return valueInPercent * 10.0;
        } else {
            return valueInPercent; // Процент
        }
    }

    public static String formatResult(double value) {
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            return "0";
        }
        return df.format(value);
    }
}