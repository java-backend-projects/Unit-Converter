package ru.sug4chy.unitconverter.service.implementation;

import org.springframework.stereotype.Service;
import ru.sug4chy.unitconverter.model.LengthMeasurementUnit;
import ru.sug4chy.unitconverter.model.MeasurementUnit;
import ru.sug4chy.unitconverter.model.TemperatureMeasurementUnit;
import ru.sug4chy.unitconverter.model.WeightMeasurementUnit;
import ru.sug4chy.unitconverter.service.UnitConverter;

@Service
public class UnitConverterImpl implements UnitConverter {

    @Override
    public double convert(double value, MeasurementUnit<?> srcUnit, MeasurementUnit<?> dstUnit) {
        return switch (srcUnit) {
            case LengthMeasurementUnit l -> l.convertTo((LengthMeasurementUnit) dstUnit, value);
            case WeightMeasurementUnit w -> w.convertTo((WeightMeasurementUnit) dstUnit, value);
            case TemperatureMeasurementUnit t -> t.convertTo((TemperatureMeasurementUnit) dstUnit, value);
            default -> throw new IllegalStateException("Unexpected value: " + srcUnit);
        };
    }
}
