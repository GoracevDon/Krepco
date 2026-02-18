package com.example.krepco.data;

import com.example.krepco.models.Unit;
import com.example.krepco.models.UnitSubcategory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitDataProvider {
    private static UnitDataProvider instance;
    private final Map<UnitSubcategory, List<Unit>> unitsBySubcategory;

    private UnitDataProvider() {
        unitsBySubcategory = new HashMap<>();
        initializeUnits();
    }

    public static UnitDataProvider getInstance() {
        if (instance == null) {
            instance = new UnitDataProvider();
        }
        return instance;
    }

    private void initializeUnits() {
        // Геометрические - Длина
        List<Unit> lengthUnits = new ArrayList<>();
        lengthUnits.add(new Unit("Километр", 1000.0, UnitSubcategory.LENGTH));
        lengthUnits.add(new Unit("Метр", 1.0, UnitSubcategory.LENGTH));
        lengthUnits.add(new Unit("Сантиметр", 0.01, UnitSubcategory.LENGTH));
        lengthUnits.add(new Unit("Миллиметр", 0.001, UnitSubcategory.LENGTH));
        lengthUnits.add(new Unit("Дециметр", 0.1, UnitSubcategory.LENGTH));
        lengthUnits.add(new Unit("Микрометр", 0.000001, UnitSubcategory.LENGTH));
        lengthUnits.add(new Unit("Нанометр", 0.000000001, UnitSubcategory.LENGTH));
        unitsBySubcategory.put(UnitSubcategory.LENGTH, lengthUnits);

        // Геометрические - Площадь
        List<Unit> areaUnits = new ArrayList<>();
        areaUnits.add(new Unit("Квадратный километр", 1_000_000.0, UnitSubcategory.AREA));
        areaUnits.add(new Unit("Квадратный метр", 1.0, UnitSubcategory.AREA));
        areaUnits.add(new Unit("Квадратный сантиметр", 0.0001, UnitSubcategory.AREA));
        areaUnits.add(new Unit("Квадратный миллиметр", 0.000001, UnitSubcategory.AREA));
        areaUnits.add(new Unit("Ар", 100.0, UnitSubcategory.AREA));
        areaUnits.add(new Unit("Гектар", 10000.0, UnitSubcategory.AREA));
        unitsBySubcategory.put(UnitSubcategory.AREA, areaUnits);

        // Геометрические - Объем
        List<Unit> volumeUnits = new ArrayList<>();
        volumeUnits.add(new Unit("Кубический километр", 1_000_000_000.0, UnitSubcategory.VOLUME));
        volumeUnits.add(new Unit("Кубический метр", 1.0, UnitSubcategory.VOLUME));
        volumeUnits.add(new Unit("Кубический дециметр", 0.001, UnitSubcategory.VOLUME));
        volumeUnits.add(new Unit("Кубический сантиметр", 0.000001, UnitSubcategory.VOLUME));
        volumeUnits.add(new Unit("Кубический миллиметр", 0.000000001, UnitSubcategory.VOLUME));
        unitsBySubcategory.put(UnitSubcategory.VOLUME, volumeUnits);

        // Геометрические - Углы
        List<Unit> angleUnits = new ArrayList<>();
        angleUnits.add(new Unit("Градус", 1.0, UnitSubcategory.ANGLE)); // Градус как базовая для углов
        angleUnits.add(new Unit("Минута", 1.0/60.0, UnitSubcategory.ANGLE));
        angleUnits.add(new Unit("Радиан", 57.2957795, UnitSubcategory.ANGLE));
        angleUnits.add(new Unit("Миллирадиан", 0.05729578, UnitSubcategory.ANGLE));
        angleUnits.add(new Unit("Град", 0.9, UnitSubcategory.ANGLE));
        angleUnits.add(new Unit("Сантиград", 0.009, UnitSubcategory.ANGLE));
        angleUnits.add(new Unit("Оборот", 360.0, UnitSubcategory.ANGLE));
        angleUnits.add(new Unit("Полуоборот", 180.0, UnitSubcategory.ANGLE));
        unitsBySubcategory.put(UnitSubcategory.ANGLE, angleUnits);

        // Механические - Скорость линейная
        List<Unit> linearSpeedUnits = new ArrayList<>();
        linearSpeedUnits.add(new Unit("Метры в секунду", 1.0, UnitSubcategory.LINEAR_SPEED));
        linearSpeedUnits.add(new Unit("Метры в минуту", 1.0/60.0, UnitSubcategory.LINEAR_SPEED));
        linearSpeedUnits.add(new Unit("Метры в час", 1.0/3600.0, UnitSubcategory.LINEAR_SPEED));
        linearSpeedUnits.add(new Unit("Километр в секунду", 1000.0, UnitSubcategory.LINEAR_SPEED));
        linearSpeedUnits.add(new Unit("Километр в минуту", 1000.0/60.0, UnitSubcategory.LINEAR_SPEED));
        linearSpeedUnits.add(new Unit("Километр в час", 1000.0/3600.0, UnitSubcategory.LINEAR_SPEED));
        linearSpeedUnits.add(new Unit("Сантиметр в секунду", 0.01, UnitSubcategory.LINEAR_SPEED));
        linearSpeedUnits.add(new Unit("Сантиметр в минуту", 0.01/60.0, UnitSubcategory.LINEAR_SPEED));
        linearSpeedUnits.add(new Unit("Сантиметр в час", 0.01/3600.0, UnitSubcategory.LINEAR_SPEED));
        linearSpeedUnits.add(new Unit("Мили в час", 0.44704, UnitSubcategory.LINEAR_SPEED));
        linearSpeedUnits.add(new Unit("Мили в секунду", 1609.344, UnitSubcategory.LINEAR_SPEED));
        linearSpeedUnits.add(new Unit("Футы в час", 0.0000846667, UnitSubcategory.LINEAR_SPEED));
        linearSpeedUnits.add(new Unit("Футы в секунду", 0.3048, UnitSubcategory.LINEAR_SPEED));
        unitsBySubcategory.put(UnitSubcategory.LINEAR_SPEED, linearSpeedUnits);

        // Механические - Скорость угловая
        List<Unit> angularSpeedUnits = new ArrayList<>();
        angularSpeedUnits.add(new Unit("Радиан в секунду", 1.0, UnitSubcategory.ANGULAR_SPEED));
        angularSpeedUnits.add(new Unit("Радиан в минуту", 1.0/60.0, UnitSubcategory.ANGULAR_SPEED));
        angularSpeedUnits.add(new Unit("Оборот в секунду", 6.2831853, UnitSubcategory.ANGULAR_SPEED));
        angularSpeedUnits.add(new Unit("Оборот в минуту", 0.104719755, UnitSubcategory.ANGULAR_SPEED));
        angularSpeedUnits.add(new Unit("Оборот в час", 0.00174533, UnitSubcategory.ANGULAR_SPEED));
        angularSpeedUnits.add(new Unit("Градус в секунду", 0.0174533, UnitSubcategory.ANGULAR_SPEED));
        angularSpeedUnits.add(new Unit("Градус в минуту", 0.000290888, UnitSubcategory.ANGULAR_SPEED));
        angularSpeedUnits.add(new Unit("Градус в час", 0.000004848, UnitSubcategory.ANGULAR_SPEED));
        angularSpeedUnits.add(new Unit("Град в минуту", 0.0002618, UnitSubcategory.ANGULAR_SPEED));
        unitsBySubcategory.put(UnitSubcategory.ANGULAR_SPEED, angularSpeedUnits);

        // Механические - Масса
        List<Unit> massUnits = new ArrayList<>();
        massUnits.add(new Unit("Килограмм", 1.0, UnitSubcategory.MASS));
        massUnits.add(new Unit("Грамм", 0.001, UnitSubcategory.MASS));
        massUnits.add(new Unit("Тонна", 1000.0, UnitSubcategory.MASS));
        massUnits.add(new Unit("Миллиграмм", 0.000001, UnitSubcategory.MASS));
        massUnits.add(new Unit("Центнер", 100.0, UnitSubcategory.MASS));
        unitsBySubcategory.put(UnitSubcategory.MASS, massUnits);

        // Механические - Давление
        List<Unit> pressureUnits = new ArrayList<>();
        pressureUnits.add(new Unit("Паскаль", 1.0, UnitSubcategory.PRESSURE));
        pressureUnits.add(new Unit("Килопаскаль", 1000.0, UnitSubcategory.PRESSURE));
        pressureUnits.add(new Unit("Мегапаскаль", 1_000_000.0, UnitSubcategory.PRESSURE));
        pressureUnits.add(new Unit("Гигапаскаль", 1_000_000_000.0, UnitSubcategory.PRESSURE));
        pressureUnits.add(new Unit("Гектопаскаль", 100.0, UnitSubcategory.PRESSURE));
        unitsBySubcategory.put(UnitSubcategory.PRESSURE, pressureUnits);

        // Временные - Длительность классические
        List<Unit> classicDurationUnits = new ArrayList<>();
        classicDurationUnits.add(new Unit("Год", 31536000.0, UnitSubcategory.CLASSIC_DURATION));
        classicDurationUnits.add(new Unit("Месяц", 2592000.0, UnitSubcategory.CLASSIC_DURATION));
        classicDurationUnits.add(new Unit("Неделя", 604800.0, UnitSubcategory.CLASSIC_DURATION));
        classicDurationUnits.add(new Unit("День", 86400.0, UnitSubcategory.CLASSIC_DURATION));
        classicDurationUnits.add(new Unit("Час", 3600.0, UnitSubcategory.CLASSIC_DURATION));
        classicDurationUnits.add(new Unit("Минута", 60.0, UnitSubcategory.CLASSIC_DURATION));
        classicDurationUnits.add(new Unit("Секунда", 1.0, UnitSubcategory.CLASSIC_DURATION));
        classicDurationUnits.add(new Unit("Квартал", 7776000.0, UnitSubcategory.CLASSIC_DURATION));
        classicDurationUnits.add(new Unit("Семестр", 15552000.0, UnitSubcategory.CLASSIC_DURATION));
        classicDurationUnits.add(new Unit("Декада", 864000.0, UnitSubcategory.CLASSIC_DURATION));
        unitsBySubcategory.put(UnitSubcategory.CLASSIC_DURATION, classicDurationUnits);

        // Временные - Длительность высокоточные
        List<Unit> preciseDurationUnits = new ArrayList<>();
        preciseDurationUnits.add(new Unit("Секунда", 1.0, UnitSubcategory.PRECISE_DURATION));
        preciseDurationUnits.add(new Unit("Миллисекунда", 0.001, UnitSubcategory.PRECISE_DURATION));
        preciseDurationUnits.add(new Unit("Микросекунда", 0.000001, UnitSubcategory.PRECISE_DURATION));
        preciseDurationUnits.add(new Unit("Наносекунда", 0.000000001, UnitSubcategory.PRECISE_DURATION));
        preciseDurationUnits.add(new Unit("Пикосекунда", 0.000000000001, UnitSubcategory.PRECISE_DURATION));
        unitsBySubcategory.put(UnitSubcategory.PRECISE_DURATION, preciseDurationUnits);

        // Временные - Частота
        List<Unit> frequencyUnits = new ArrayList<>();
        frequencyUnits.add(new Unit("Герц", 1.0, UnitSubcategory.FREQUENCY));
        frequencyUnits.add(new Unit("Килогерц", 1000.0, UnitSubcategory.FREQUENCY));
        frequencyUnits.add(new Unit("Мегагерц", 1_000_000.0, UnitSubcategory.FREQUENCY));
        frequencyUnits.add(new Unit("Гигагерц", 1_000_000_000.0, UnitSubcategory.FREQUENCY));
        frequencyUnits.add(new Unit("Терагерц", 1_000_000_000_000.0, UnitSubcategory.FREQUENCY));
        unitsBySubcategory.put(UnitSubcategory.FREQUENCY, frequencyUnits);

        // Временные - Интервалы
        List<Unit> intervalUnits = new ArrayList<>();
        intervalUnits.add(new Unit("Секунда", 1.0, UnitSubcategory.INTERVALS));
        intervalUnits.add(new Unit("Минута", 60.0, UnitSubcategory.INTERVALS));
        intervalUnits.add(new Unit("Час", 3600.0, UnitSubcategory.INTERVALS));
        intervalUnits.add(new Unit("День", 86400.0, UnitSubcategory.INTERVALS));
        intervalUnits.add(new Unit("Неделя", 604800.0, UnitSubcategory.INTERVALS));
        intervalUnits.add(new Unit("Месяц", 2592000.0, UnitSubcategory.INTERVALS));
        intervalUnits.add(new Unit("Год", 31536000.0, UnitSubcategory.INTERVALS));
        unitsBySubcategory.put(UnitSubcategory.INTERVALS, intervalUnits);

        // Энергетические - Механическая энергия
        List<Unit> mechanicalEnergyUnits = new ArrayList<>();
        mechanicalEnergyUnits.add(new Unit("Джоуль", 1.0, UnitSubcategory.MECHANICAL_ENERGY));
        mechanicalEnergyUnits.add(new Unit("Килоджоуль", 1000.0, UnitSubcategory.MECHANICAL_ENERGY));
        mechanicalEnergyUnits.add(new Unit("Мегаджоуль", 1_000_000.0, UnitSubcategory.MECHANICAL_ENERGY));
        mechanicalEnergyUnits.add(new Unit("Килограмм-сила-метр", 9.80665, UnitSubcategory.MECHANICAL_ENERGY));
        unitsBySubcategory.put(UnitSubcategory.MECHANICAL_ENERGY, mechanicalEnergyUnits);

        // Энергетические - Тепловая энергия
        List<Unit> thermalEnergyUnits = new ArrayList<>();
        thermalEnergyUnits.add(new Unit("Джоуль", 1.0, UnitSubcategory.THERMAL_ENERGY));
        thermalEnergyUnits.add(new Unit("Килоджоуль", 1000.0, UnitSubcategory.THERMAL_ENERGY));
        thermalEnergyUnits.add(new Unit("Мегаджоуль", 1_000_000.0, UnitSubcategory.THERMAL_ENERGY));
        thermalEnergyUnits.add(new Unit("Килограмм-сила-метр", 9.80665, UnitSubcategory.THERMAL_ENERGY));
        unitsBySubcategory.put(UnitSubcategory.THERMAL_ENERGY, thermalEnergyUnits);

        // Энергетические - Электрическая энергия
        List<Unit> electricalEnergyUnits = new ArrayList<>();
        electricalEnergyUnits.add(new Unit("Ватт-час", 3600.0, UnitSubcategory.ELECTRICAL_ENERGY));
        electricalEnergyUnits.add(new Unit("Киловатт-час", 3_600_000.0, UnitSubcategory.ELECTRICAL_ENERGY));
        electricalEnergyUnits.add(new Unit("Мегаватт-час", 3_600_000_000.0, UnitSubcategory.ELECTRICAL_ENERGY));
        electricalEnergyUnits.add(new Unit("Гигаватт-час", 3_600_000_000_000.0, UnitSubcategory.ELECTRICAL_ENERGY));
        electricalEnergyUnits.add(new Unit("Джоуль", 1.0, UnitSubcategory.ELECTRICAL_ENERGY));
        unitsBySubcategory.put(UnitSubcategory.ELECTRICAL_ENERGY, electricalEnergyUnits);

        // Энергетические - Мощность
        List<Unit> powerUnits = new ArrayList<>();
        powerUnits.add(new Unit("Ватт", 1.0, UnitSubcategory.POWER));
        powerUnits.add(new Unit("Киловатт", 1000.0, UnitSubcategory.POWER));
        powerUnits.add(new Unit("Лошадиная сила", 735.49875, UnitSubcategory.POWER));
        unitsBySubcategory.put(UnitSubcategory.POWER, powerUnits);

        // Строительные - Конструкционная нагрузка
        List<Unit> constructionLoadUnits = new ArrayList<>();
        constructionLoadUnits.add(new Unit("Килограмм на квадратный метр", 1.0, UnitSubcategory.CONSTRUCTION_LOAD));
        constructionLoadUnits.add(new Unit("Тонна на квадратный метр", 1000.0, UnitSubcategory.CONSTRUCTION_LOAD));
        constructionLoadUnits.add(new Unit("Ньютон на квадратный метр", 0.10197, UnitSubcategory.CONSTRUCTION_LOAD));
        constructionLoadUnits.add(new Unit("Килопаскаль", 101.97, UnitSubcategory.CONSTRUCTION_LOAD));
        constructionLoadUnits.add(new Unit("Мегапаскаль", 101970.0, UnitSubcategory.CONSTRUCTION_LOAD));
        unitsBySubcategory.put(UnitSubcategory.CONSTRUCTION_LOAD, constructionLoadUnits);

        // Строительные - Конструкционный уклон
        List<Unit> constructionSlopeUnits = new ArrayList<>();
        constructionSlopeUnits.add(new Unit("Процент", 1.0, UnitSubcategory.CONSTRUCTION_SLOPE));
        constructionSlopeUnits.add(new Unit("Градус", 1.0, UnitSubcategory.CONSTRUCTION_SLOPE)); // Требуется особая конвертация
        constructionSlopeUnits.add(new Unit("Сантиметр на метр", 1.0, UnitSubcategory.CONSTRUCTION_SLOPE));
        constructionSlopeUnits.add(new Unit("Миллиметр на метр", 0.1, UnitSubcategory.CONSTRUCTION_SLOPE));
        constructionSlopeUnits.add(new Unit("Промилле", 0.1, UnitSubcategory.CONSTRUCTION_SLOPE));
        unitsBySubcategory.put(UnitSubcategory.CONSTRUCTION_SLOPE, constructionSlopeUnits);

        // Строительные - Сопротивление материалов
        List<Unit> materialStrengthUnits = new ArrayList<>();
        materialStrengthUnits.add(new Unit("Килограмм на квадратный метр", 1.0, UnitSubcategory.MATERIAL_STRENGTH));
        materialStrengthUnits.add(new Unit("Тонна на квадратный метр", 1000.0, UnitSubcategory.MATERIAL_STRENGTH));
        materialStrengthUnits.add(new Unit("Ньютон на квадратный метр", 0.10197, UnitSubcategory.MATERIAL_STRENGTH));
        materialStrengthUnits.add(new Unit("Килопаскаль", 101.97, UnitSubcategory.MATERIAL_STRENGTH));
        materialStrengthUnits.add(new Unit("Мегапаскаль", 101970.0, UnitSubcategory.MATERIAL_STRENGTH));
        unitsBySubcategory.put(UnitSubcategory.MATERIAL_STRENGTH, materialStrengthUnits);

        // Строительные - Объемный вес
        List<Unit> volumeWeightUnits = new ArrayList<>();
        volumeWeightUnits.add(new Unit("Килограмм на кубический метр", 1.0, UnitSubcategory.VOLUME_WEIGHT));
        volumeWeightUnits.add(new Unit("Тонна на кубический метр", 1000.0, UnitSubcategory.VOLUME_WEIGHT));
        volumeWeightUnits.add(new Unit("Грамм на кубический метр", 0.001, UnitSubcategory.VOLUME_WEIGHT));
        volumeWeightUnits.add(new Unit("Килограмм-сила на квадратный сантиметр", 10000.0, UnitSubcategory.VOLUME_WEIGHT));
        volumeWeightUnits.add(new Unit("Ньютон на квадратный миллиметр", 101971.6, UnitSubcategory.VOLUME_WEIGHT));
        unitsBySubcategory.put(UnitSubcategory.VOLUME_WEIGHT, volumeWeightUnits);

        // Экономические - Валюта
        List<Unit> currencyUnits = new ArrayList<>();
        currencyUnits.add(new Unit("Рубль", 1.0, UnitSubcategory.CURRENCY));
        currencyUnits.add(new Unit("Белорусский рубль", 1.0, UnitSubcategory.CURRENCY)); // Упрощенно
        currencyUnits.add(new Unit("Доллар", 90.0, UnitSubcategory.CURRENCY));
        currencyUnits.add(new Unit("Канадский доллар", 66.0, UnitSubcategory.CURRENCY));
        currencyUnits.add(new Unit("Фунт стерлингов", 115.0, UnitSubcategory.CURRENCY));
        currencyUnits.add(new Unit("Евро", 98.0, UnitSubcategory.CURRENCY));
        currencyUnits.add(new Unit("Юань", 12.5, UnitSubcategory.CURRENCY));
        currencyUnits.add(new Unit("Израильский шекель", 25.0, UnitSubcategory.CURRENCY));
        unitsBySubcategory.put(UnitSubcategory.CURRENCY, currencyUnits);

        // Экономические - Зарплата
        List<Unit> salaryUnits = new ArrayList<>();
        salaryUnits.add(new Unit("Рубль в час", 1.0, UnitSubcategory.SALARY));
        salaryUnits.add(new Unit("Рубль в день", 1.0/8.0, UnitSubcategory.SALARY)); // При 8-часовом дне
        salaryUnits.add(new Unit("Рубль в месяц", 1.0/160.0, UnitSubcategory.SALARY)); // При 160 часах в месяц
        salaryUnits.add(new Unit("Рубль в год", 1.0/1920.0, UnitSubcategory.SALARY)); // При 1920 часах в год
        salaryUnits.add(new Unit("Доллар в час", 90.0, UnitSubcategory.SALARY));
        salaryUnits.add(new Unit("Доллар в день", 90.0/8.0, UnitSubcategory.SALARY));
        salaryUnits.add(new Unit("Доллар в месяц", 90.0/160.0, UnitSubcategory.SALARY));
        salaryUnits.add(new Unit("Доллар в год", 90.0/1920.0, UnitSubcategory.SALARY));
        salaryUnits.add(new Unit("Евро в час", 98.0, UnitSubcategory.SALARY));
        salaryUnits.add(new Unit("Евро в день", 98.0/8.0, UnitSubcategory.SALARY));
        unitsBySubcategory.put(UnitSubcategory.SALARY, salaryUnits);

        // Экономические - Процентная ставка
        List<Unit> interestRateUnits = new ArrayList<>();
        interestRateUnits.add(new Unit("Процент годовых", 1.0, UnitSubcategory.INTEREST_RATE));
        interestRateUnits.add(new Unit("Процент месячных", 12.0, UnitSubcategory.INTEREST_RATE));
        interestRateUnits.add(new Unit("Процент дневных", 365.0, UnitSubcategory.INTEREST_RATE));
        interestRateUnits.add(new Unit("Процент за период", 1.0, UnitSubcategory.INTEREST_RATE));
        unitsBySubcategory.put(UnitSubcategory.INTEREST_RATE, interestRateUnits);

        // Экономические - Весовая себестоимость
        List<Unit> weightCostUnits = new ArrayList<>();
        weightCostUnits.add(new Unit("Рубль за тонну", 1.0, UnitSubcategory.WEIGHT_COST));
        weightCostUnits.add(new Unit("Рубль за центнер", 10.0, UnitSubcategory.WEIGHT_COST));
        weightCostUnits.add(new Unit("Рубль за килограмм", 1000.0, UnitSubcategory.WEIGHT_COST));
        weightCostUnits.add(new Unit("Рубль за грамм", 1_000_000.0, UnitSubcategory.WEIGHT_COST));
        weightCostUnits.add(new Unit("Рубль за микрограмм", 1_000_000_000.0, UnitSubcategory.WEIGHT_COST));
        weightCostUnits.add(new Unit("Доллар за килограмм", 90_000.0, UnitSubcategory.WEIGHT_COST));
        weightCostUnits.add(new Unit("Доллар за грамм", 90_000_000.0, UnitSubcategory.WEIGHT_COST));
        unitsBySubcategory.put(UnitSubcategory.WEIGHT_COST, weightCostUnits);
    }

    public List<Unit> getUnitsForSubcategory(UnitSubcategory subcategory) {
        return unitsBySubcategory.getOrDefault(subcategory, new ArrayList<>());
    }
}
