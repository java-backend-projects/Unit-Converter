package ru.sug4chy.unitconverter.model;

public interface MeasurementUnit<TUnits extends MeasurementUnit<TUnits>> {
     double convertTo(TUnits to, double value);
     String getName();
     String getShortCut();
}
