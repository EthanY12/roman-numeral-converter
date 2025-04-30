package com.romannumeralconverter.service;

import com.romannumeralconverter.dto.ConversionResponseDto;
import com.romannumeralconverter.dto.NumeralConversionRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RomanNumeralServiceIT {

    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void testValidConversionThroughController() {
        NumeralConversionRequestDTO request = new NumeralConversionRequestDTO("X");
        ResponseEntity<ConversionResponseDto> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/convertRomanToDecimal", request, ConversionResponseDto.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(10, responseEntity.getBody().decimalValue());
    }

    @Test
    public void testInvalidConversionThroughController() {
        NumeralConversionRequestDTO request = new NumeralConversionRequestDTO("XXXXXXXXX");
        ResponseEntity<ConversionResponseDto> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/convertRomanToDecimal", request, ConversionResponseDto.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
