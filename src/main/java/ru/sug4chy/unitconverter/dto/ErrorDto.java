package ru.sug4chy.unitconverter.dto;

import lombok.With;

@With
public record ErrorDto(String message, String url) {
}
