package org.roman.tractor.command;

import org.roman.tractor.model.Border;
import org.roman.tractor.model.Location;
import org.roman.tractor.model.Orientation;
import org.roman.tractor.model.Position;
import org.roman.tractor.exception.TractorInDitchException;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.UnaryOperator;

import static org.roman.tractor.model.Orientation.*;

public class ForwardCommand implements Command {

    private static final Map<Orientation, UnaryOperator<Position>> moveMap;

    static {
        moveMap = new EnumMap<>(Orientation.class);
        moveMap.put(NORTH, p -> new Position(p.getX(), p.getY() + 1));
        moveMap.put(SOUTH, p -> new Position(p.getX(), p.getY() - 1));
        moveMap.put(WEST, p -> new Position(p.getX() - 1, p.getY()));
        moveMap.put(EAST, p -> new Position(p.getX() + 1, p.getY()));
    }

    @Override
    public void execute(Location location, Border border) {
        UnaryOperator<Position> moveOperator = moveMap.get(location.getOrientation());
        Position newPosition = moveOperator.apply(location.getPosition());
        if (isInDitch(newPosition, border)) {
            throw new TractorInDitchException();
        }
        location.setPosition(newPosition);
    }

    private boolean isInDitch(Position position, Border border) {
        return position.getX() > border.getXBorder() || position.getY() > border.getYBorder();
    }
}
