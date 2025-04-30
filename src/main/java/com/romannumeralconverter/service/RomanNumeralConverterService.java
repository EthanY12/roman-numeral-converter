package com.romannumeralconverter.service;

import com.romannumeralconverter.dto.NumeralConversionRequestDTO;

import java.util.Map;

public interface RomanNumeralConverterService {
    int convertToDecimal(NumeralConversionRequestDTO romanNumeralDTO);
    Map<String, Integer> getAllConversions();
    
}
