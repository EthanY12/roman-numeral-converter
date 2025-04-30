package com.romannumeralconverter.service;

import com.romannumeralconverter.dto.NumeralConversionRequestDTO;
import com.romannumeralconverter.error.InvalidRomanNumeralException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class RomanNumeralConverterServiceImplTest {

    @InjectMocks
    private RomanNumeralConverterServiceImpl conversionService;
    
    @Mock
    private RomanNumeralValidator romanNumeralValidator;
    
    private static Stream<Arguments> shouldConvertRomanNumeral() {return Stream.of(Arguments.of("I", 1), Arguments.of("IV", 4), Arguments.of("V", 5), Arguments.of("VI", 6), Arguments.of("VIII", 8), Arguments.of("IX", 9), Arguments.of("X", 10), Arguments.of("Xxvi", 26), Arguments.of("XL", 40),  Arguments.of("L", 50), Arguments.of("LVi", 56), Arguments.of("CVII", 107),Arguments.of("MDCCC", 1800), Arguments.of("MCM", 1900), Arguments.of("MM", 2000),Arguments.of("CD", 400), Arguments.of("D", 500), Arguments.of("DI", 501));
    }
    @ParameterizedTest
    @MethodSource("shouldConvertRomanNumeral")
    void shouldConvertRomanNumeral( String romanNumeral, Integer expectedDecimalValue ) {
        int result = conversionService.convertToDecimal(new NumeralConversionRequestDTO(romanNumeral));
        assertThat(result).isEqualTo(expectedDecimalValue);
    }

    private static Stream<Arguments> invalidRomanNumerals() {return Stream.of(Arguments.of("z"), Arguments.of("xz"));
    }
    @ParameterizedTest
    @MethodSource("invalidRomanNumerals")
    void invalidRomanNumeral(String romanNumeral) {
        assertThrows(InvalidRomanNumeralException.class, () -> conversionService.convertToDecimal(new NumeralConversionRequestDTO(romanNumeral)));        }
}
