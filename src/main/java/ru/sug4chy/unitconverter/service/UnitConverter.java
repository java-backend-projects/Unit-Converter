package ru.sug4chy.unitconverter.service;

import ru.sug4chy.unitconverter.model.MeasurementUnit;

public interface UnitConverter {
    double convert(double value, MeasurementUnit<?> srcUnit, MeasurementUnit<?> dstUnit);
}
