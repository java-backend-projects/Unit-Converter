package ru.sug4chy.unitconverter.model;

import java.util.function.DoubleUnaryOperator;

public enum TemperatureMeasurementUnit implements MeasurementUnit<TemperatureMeasurementUnit> {

    CELSIUS(celsius -> celsius, celsius -> celsius, "^C"),
    FAHRENHEIT(fahrenheit -> (fahrenheit - 32) / 1.8, celsius -> (celsius * 1.8) + 32, "^F"),
    KELVIN(kelvin -> kelvin - 273.15, celsius -> celsius + 273.15, "K");

    private final DoubleUnaryOperator toCIConverter;
    private final DoubleUnaryOperator fromCIConverter;
    private final String shortCut;

    TemperatureMeasurementUnit(
            DoubleUnaryOperator toCIConverter,
            DoubleUnaryOperator fromCIConverter,
            String shortCut
    ) {
        this.toCIConverter = toCIConverter;
        this.fromCIConverter = fromCIConverter;
        this.shortCut = shortCut;
    }

    @Override
    public double convertTo(TemperatureMeasurementUnit to, double value) {
        return to.fromCIConverter.applyAsDouble(
                toCIConverter.applyAsDouble(value)
        );
    }

    @Override
    public String getName() {
        String lowerCaseName = this.name().toLowerCase();
        return Character.toUpperCase(lowerCaseName.toCharArray()[0]) + lowerCaseName.substring(1);
    }

    @Override
    public String getShortCut() {
        return this.shortCut;
    }
}
