package ru.sug4chy.unitconverter.model;

import java.util.function.DoubleUnaryOperator;

public enum LengthMeasurementUnit implements MeasurementUnit<LengthMeasurementUnit> {

    MILLIMETER(millimeter -> millimeter / 1000, meter -> meter * 1000, "mm"),
    CENTIMETER(centimeter -> centimeter / 100, meter -> meter * 100, "cm"),
    METER(meter -> meter, meter -> meter, "m"),
    KILOMETER(kilometer -> kilometer * 1000, meter -> meter / 1000, "km"),
    INCH(inch -> inch * 0.0254, meter -> meter / 0.0254, "in"),
    FOOT(foot -> foot * 0.3048, meter -> meter / 0.3048, "ft"),
    YARD(yard -> yard / 1.0936, meter -> meter * 1.0936, "yd"),
    MILE(mile -> mile * 1609.344, meter -> meter / 1609.344, "mi");

    private final DoubleUnaryOperator toCIConverter;
    private final DoubleUnaryOperator fromCIConverter;
    private final String shortCut;

    LengthMeasurementUnit(DoubleUnaryOperator toCIConverter,
                          DoubleUnaryOperator fromCIConverter,
                          String shortCut
    ) {
        this.toCIConverter = toCIConverter;
        this.fromCIConverter = fromCIConverter;
        this.shortCut = shortCut;
    }

    @Override
    public double convertTo(LengthMeasurementUnit to, double value) {
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
