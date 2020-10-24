package org.roman.tractor.model;

public class Border {

    private final int xBorder;
    private final int yBorder;

    public Border(int xBorder, int yBorder) {
        this.xBorder = xBorder;
        this.yBorder = yBorder;
    }

    public int getXBorder() {
        return xBorder;
    }

    public int getYBorder() {
        return yBorder;
    }
}
