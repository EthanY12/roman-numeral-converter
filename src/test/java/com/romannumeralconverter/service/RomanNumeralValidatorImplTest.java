package com.romannumeralconverter.service;

import com.romannumeralconverter.error.InvalidRomanNumeralException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class RomanNumeralValidatorImplTest {

    @InjectMocks
    private RomanNumeralValidatorImpl romanNumeralValidator;
    
    @Test
    void testValidNumeralOne() {
        assertDoesNotThrow(() -> romanNumeralValidator.isRomanNumeralValid("i"));
    }
    @Test
    void testInvalidNumeral() {
        assertThrows(InvalidRomanNumeralException.class, () -> romanNumeralValidator.isRomanNumeralValid("Y"));
    }
}
