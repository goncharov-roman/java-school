package lesson1.hierarchy;

public class Circle extends PlanarFigure {

    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getSquare() {
        return Math.PI * radius * radius;
    }
}
