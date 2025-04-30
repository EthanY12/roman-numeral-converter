package com.romannumeralconverter.error;

public class InvalidRomanNumeralException extends RuntimeException {
    public InvalidRomanNumeralException() {
        super();
    }

    public InvalidRomanNumeralException(String message) {
        super(message);
    }

    public InvalidRomanNumeralException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRomanNumeralException(Throwable cause) {
        super(cause);
    }
}

