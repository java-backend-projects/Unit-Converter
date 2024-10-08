package ru.sug4chy.unitconverter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sug4chy.unitconverter.model.LengthMeasurementUnit;
import ru.sug4chy.unitconverter.model.MeasurementUnit;
import ru.sug4chy.unitconverter.model.TemperatureMeasurementUnit;
import ru.sug4chy.unitconverter.model.WeightMeasurementUnit;
import ru.sug4chy.unitconverter.service.UnitConverter;

@Controller
@RequestMapping("/unit-conversion")
@RequiredArgsConstructor
public class UnitConversionController {

    private final UnitConverter converter;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(defaultValue = "length") String unitType,
                        @RequestParam(defaultValue = "form") String viewMode
    ) {
        model.addAttribute("currentTab", unitType);
        model.addAttribute("viewMode", viewMode);
        model.addAttribute("units", switch (unitType) {
            case "length" -> LengthMeasurementUnit.values();
            case "weight" -> WeightMeasurementUnit.values();
            case "temperature" -> TemperatureMeasurementUnit.values();
            default -> throw new IllegalArgumentException("Unexpected value (argument unitType): " + unitType);
        });

        return "index";
    }

    @PostMapping("/convert")
    public String convert(Model model,
                          @RequestParam double value,
                          @RequestParam MeasurementUnit<?> srcUnit,
                          @RequestParam MeasurementUnit<?> dstUnit,
                          @RequestParam String unitType
    ) {
        double convertedValue = converter.convert(value, srcUnit, dstUnit);

        model.addAttribute("viewMode", "result");
        model.addAttribute("value", value);
        model.addAttribute("srcUnit", srcUnit);
        model.addAttribute("convertedValue", convertedValue);
        model.addAttribute("dstUnit", dstUnit);
        model.addAttribute("currentTab", unitType);

        return "index";
    }
}