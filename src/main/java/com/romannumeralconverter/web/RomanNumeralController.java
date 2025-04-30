package com.romannumeralconverter.web;

import com.romannumeralconverter.dto.ConversionResponseDto;
import com.romannumeralconverter.dto.NumeralConversionRequestDTO;
import com.romannumeralconverter.service.RomanNumeralConverterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class RomanNumeralController {
    private final RomanNumeralConverterService romanNumeralConverterService;
    
    @Autowired
    public RomanNumeralController(RomanNumeralConverterService romanNumeralConverterService) {
        this.romanNumeralConverterService = romanNumeralConverterService;
    }
    
    @PostMapping(value = "/convertRomanToDecimal", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ConversionResponseDto> convertToDecimal(
            @Valid @RequestBody NumeralConversionRequestDTO romanNumeral) {
        
        int decimalResult = romanNumeralConverterService.convertToDecimal(romanNumeral);
        return new ResponseEntity<>(
                new ConversionResponseDto(decimalResult), HttpStatus.OK);  // Avoid making the conversion again, just use the decimalResult.
    }

    @GetMapping("/getResults")
    public ResponseEntity<Map<String, Integer>> getResults() {
        return ResponseEntity.ok(romanNumeralConverterService.getAllConversions());
    }
}
