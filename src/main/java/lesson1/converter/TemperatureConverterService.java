package lesson1.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TemperatureConverterService {

    public static Double convertToFahrenheit(Double celsius) {
        Double fahrenheit = celsius * 1.8 + 32;
        return BigDecimal.valueOf(fahrenheit).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static Double convertToKelvin(Double celsius) {
        Double kelvin = celsius + 273.15;
        return BigDecimal.valueOf(kelvin).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
