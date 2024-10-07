package ru.sug4chy.unitconverter.spring.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.sug4chy.unitconverter.model.LengthMeasurementUnit;
import ru.sug4chy.unitconverter.model.MeasurementUnit;
import ru.sug4chy.unitconverter.model.TemperatureMeasurementUnit;
import ru.sug4chy.unitconverter.model.WeightMeasurementUnit;

@Component
public class StringToMeasurementUnitConverter implements Converter<String, MeasurementUnit<?>> {

    @Override
    public MeasurementUnit<?> convert(@NonNull String source) {
        for (LengthMeasurementUnit l : LengthMeasurementUnit.values()) {
            if (l.name().equalsIgnoreCase(source)) {
                return l;
            }
        }

        for (WeightMeasurementUnit w : WeightMeasurementUnit.values()) {
            if (w.name().equalsIgnoreCase(source)) {
                return w;
            }
        }

        for (TemperatureMeasurementUnit t : TemperatureMeasurementUnit.values()) {
            if (t.name().equalsIgnoreCase(source)) {
                return t;
            }
        }

        throw new IllegalArgumentException("Unknown Measurement Unit: " + source);
    }
}
