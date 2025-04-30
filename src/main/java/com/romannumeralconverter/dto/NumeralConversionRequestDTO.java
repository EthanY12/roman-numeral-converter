package com.romannumeralconverter.dto;

import jakarta.validation.constraints.NotBlank;

public record NumeralConversionRequestDTO(
        @NotBlank(message = "Roman numerals must not be blank") String romanNumerals) {
}
