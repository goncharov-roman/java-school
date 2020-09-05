package lesson1.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterServiceTest {

    @Test
    public void testConvertToFahrenheit() {
        assertEquals(-58.0, TemperatureConverterService.convertToFahrenheit(-50.0));
        assertEquals(23.0, TemperatureConverterService.convertToFahrenheit(-5.0));
        assertEquals(-459.67, TemperatureConverterService.convertToFahrenheit(-273.15));
    }
}