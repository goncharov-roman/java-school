package lesson1.hierarchy;

public class Rect extends Polygon {

    protected final static int NUM_OF_SIDES = 4;

    protected Double length;
    protected Double width;

    public Rect(Double length, Double width) {
        this.length = length;
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getSquare() {
        return width * length;
    }

    @Override
    int getNumberOfSides() {
        return NUM_OF_SIDES;
    }
}
