package lesson2.cars;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarGroupingServiceTest {

    @Test
    void testGroupByType() {
        Car logan = new Car("Renault Logan", "Sedan");
        Car arkana = new Car("Renault Arkana", "Crossover");
        Car kaptur = new Car("Renault Kaptur", "Crossover");
        Car vesta = new Car("Lada Vesta SW Cross", "Universal");
        Car granta = new Car("Lada Granta", "Liftback");
        Car mercedes = new Car("Mercedes Benz A class", "Sedan");
        Car rapid = new Car("Skoda Rapid", "Sedan");

        List<Car> cars = Arrays.asList(
                logan,
                arkana,
                kaptur,
                vesta,
                granta,
                mercedes,
                rapid
        );

        Map<String, List<Car>> expected = new HashMap<>();
        expected.put("Sedan", Arrays.asList(logan, mercedes, rapid));
        expected.put("Crossover", Arrays.asList(arkana, kaptur));
        expected.put("Liftback", Collections.singletonList(granta));
        expected.put("Universal", Collections.singletonList(vesta));

        assertEquals(expected, CarGroupingService.groupByType(cars));
    }
}