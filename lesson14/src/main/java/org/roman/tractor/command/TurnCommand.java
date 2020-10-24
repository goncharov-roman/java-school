package org.roman.tractor.command;

import org.roman.tractor.model.Border;
import org.roman.tractor.model.Location;
import org.roman.tractor.model.Orientation;

import java.util.EnumMap;
import java.util.Map;

import static org.roman.tractor.model.Orientation.*;

public class TurnCommand implements Command {

    private static final Map<Orientation, Orientation> turnMap;

    static {
        turnMap = new EnumMap<>(Orientation.class);
        turnMap.put(NORTH, EAST);
        turnMap.put(SOUTH, WEST);
        turnMap.put(WEST, NORTH);
        turnMap.put(EAST, SOUTH);
    }

    @Override
    public void execute(Location location, Border border) {
        Orientation newOrientation = turnMap.get(location.getOrientation());
        location.setOrientation(newOrientation);
    }
}
