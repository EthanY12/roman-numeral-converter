package com.romannumeralconverter.service;

import com.romannumeralconverter.error.InvalidRomanNumeralException;
import org.springframework.stereotype.Service;

@Service
public class RomanNumeralValidatorImpl implements RomanNumeralValidator {

    @Override
    public boolean isRomanNumeralValid(String romanNumeral) {
        if (!romanNumeral.toUpperCase().matches("^(M{0,2})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")) {
            throw new InvalidRomanNumeralException("Invalid Roman numeral: " + romanNumeral);
        }
        return true;
    }
}
