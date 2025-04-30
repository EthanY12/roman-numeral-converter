package com.romannumeralconverter.controller;

import com.romannumeralconverter.dto.ConversionResponseDto;
import com.romannumeralconverter.dto.NumeralConversionRequestDTO;
import com.romannumeralconverter.service.RomanNumeralConverterService;
import com.romannumeralconverter.web.RomanNumeralController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RomanNumeralControllerTest {

	@Mock
	private RomanNumeralConverterService romanNumeralConverterService;
	
	@InjectMocks
	private RomanNumeralController romanNumeralController;
	
	@Test
	public void romanNumeralOneShouldBeDecimalOne() {
		final var dto = new NumeralConversionRequestDTO("I");
		when(romanNumeralConverterService.convertToDecimal(dto)).thenReturn(1);
		final ResponseEntity<ConversionResponseDto> result;
		try {
			result = romanNumeralController.convertToDecimal(dto);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		assertTrue(result.getStatusCode().is2xxSuccessful());
		assertTrue(result.getBody().decimalValue() == 1);
	}
}
