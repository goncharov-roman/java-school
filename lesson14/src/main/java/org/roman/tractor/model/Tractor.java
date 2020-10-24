package org.roman.tractor.model;

import org.roman.tractor.command.Command;
import org.roman.tractor.command.CommandFactory;

public class Tractor {

    Location location = new Location(
            new Position(0, 0),
            Orientation.NORTH
    );

    private final Border field = new Border(5, 5);

    public void move(String command) {
        Command targetCommand = CommandFactory
            .getCommand(command)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));

        targetCommand.execute(location, field);
    }

    public int getPositionX() {
        return location.getPosition().getX();
    }

    public int getPositionY() {
        return location.getPosition().getY();
    }

    public Orientation getOrientation() {
        return location.getOrientation();
    }
}
