package org.roman.cars;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class CarGroupingService {

    public static Map<String, List<Car>> groupByType(List<Car> cars) {
        return cars.stream().collect(groupingBy(Car::getType));
    }
}
