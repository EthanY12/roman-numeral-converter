package com.romannumeralconverter.service;

import com.romannumeralconverter.dto.NumeralConversionRequestDTO;
import com.romannumeralconverter.error.InvalidRomanNumeralException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RomanNumeralConverterServiceImpl implements RomanNumeralConverterService {
    private static final Map<String, Integer> ROMAN_NUMERAL = new HashMap<>();
    
    static  {
                ROMAN_NUMERAL.put("M", 1000);
                ROMAN_NUMERAL.put("CM", 900);
                ROMAN_NUMERAL.put("D", 500);
                ROMAN_NUMERAL.put("CD", 400);
                ROMAN_NUMERAL.put("C", 100);
                ROMAN_NUMERAL.put("XC", 90);
                ROMAN_NUMERAL.put("L", 50);
                ROMAN_NUMERAL.put("XL", 40);
                ROMAN_NUMERAL.put("X", 10);
                ROMAN_NUMERAL.put("IX", 9);
                ROMAN_NUMERAL.put("V", 5);
                ROMAN_NUMERAL.put("IV", 4);
                ROMAN_NUMERAL.put("I", 1);
    }

    private final RomanNumeralValidator romanNumeralValidator;
    private final Map<String, Integer> conversionResults = new ConcurrentHashMap<>();

    public RomanNumeralConverterServiceImpl(RomanNumeralValidator romanNumeralValidator) {
        this.romanNumeralValidator = romanNumeralValidator;
    }

    public int convertToDecimal(NumeralConversionRequestDTO romanNumeralDTO) {
            
        int decimal = 0;
        
        romanNumeralValidator.isRomanNumeralValid(romanNumeralDTO.romanNumerals());
        
        String romanNumerals = romanNumeralDTO.romanNumerals().toUpperCase();
        int i = 0;
        while (i < romanNumerals.length()) {
            if (i < romanNumerals.length() - 1 && ROMAN_NUMERAL.containsKey(romanNumerals.substring(i, i+2))) {
                decimal += ROMAN_NUMERAL.get(romanNumerals.substring(i, i+2));
                i += 2;
            } else if (ROMAN_NUMERAL.containsKey(romanNumerals.substring(i, i+1))) {
                decimal += ROMAN_NUMERAL.get(romanNumerals.substring(i, i+1));
                i++;
            } else {
                throw new InvalidRomanNumeralException("Invalid roman numeral");
            }
        }
        conversionResults.put(romanNumeralDTO.romanNumerals(), decimal);
        return decimal;
    }

    @Override
    public Map<String, Integer> getAllConversions() {
        return Collections.unmodifiableMap(conversionResults);  // Non-static now
    }
}
