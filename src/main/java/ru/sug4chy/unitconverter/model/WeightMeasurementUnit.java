package ru.sug4chy.unitconverter.model;

import java.util.function.DoubleUnaryOperator;

public enum WeightMeasurementUnit implements MeasurementUnit<WeightMeasurementUnit> {

    MILLIGRAM(milligram -> milligram / 1_000_000, kilogram -> kilogram * 1_000_000, "mg"),
    GRAM(gram -> gram / 1000, kilogram -> kilogram * 1000, "g"),
    KILOGRAM(kilogram -> kilogram, kilogram -> kilogram, "kg"),
    OUNCE(ounce -> ounce / 35.27396195, kilogram -> kilogram * 35.27396195, "oz"),
    POUND(pound -> pound * 0.45359237, kilogram -> kilogram / 0.45359237, "lb");

    private final DoubleUnaryOperator toCIConverter;
    private final DoubleUnaryOperator fromCIConverter;
    private final String shortCut;

    WeightMeasurementUnit(
            DoubleUnaryOperator toCIConverter,
            DoubleUnaryOperator fromCIConverter,
            String shortCut
    ) {
        this.toCIConverter = toCIConverter;
        this.fromCIConverter = fromCIConverter;
        this.shortCut = shortCut;
    }

    @Override
    public double convertTo(WeightMeasurementUnit to, double value) {
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
