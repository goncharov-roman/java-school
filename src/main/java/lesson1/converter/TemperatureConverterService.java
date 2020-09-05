package lesson1.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TemperatureConverterService {

    public static Double convertToFahrenheit(Double celsius) {
        Double fahrenheit = celsius * 1.8 + 32;
        return BigDecimal.valueOf(fahrenheit).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
