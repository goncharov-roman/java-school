package org.roman.hierarchy;

public class Square extends Rect {

    public Square(Double side) {
        super(side, side);
    }

    public Double getSide() {
        return length;
    }
}
