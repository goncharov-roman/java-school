package lesson1.hierarchy;

import java.util.List;

import static java.lang.Math.sqrt;

public class Triangle extends Polygon {

    private List<Integer> sides;

    public Triangle(List<Integer> sides) {
        this.sides = sides;
    }

    public List<Integer> getSides() {
        return sides;
    }

    public void setSides(List<Integer> sides) {
        this.sides = sides;
    }

    public Double getSquare() {
        int semiperimeter = sides.stream().reduce(0, (subtotal, side) -> subtotal + side) / 2;
        return sqrt(
                semiperimeter
                * (semiperimeter - sides.get(0))
                * (semiperimeter - sides.get(1))
                * (semiperimeter - sides.get(2))
        );
    }
}
