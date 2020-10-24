package org.roman.tractor.command;

import org.roman.tractor.model.Border;
import org.roman.tractor.model.Location;

public interface Command {

    void execute(Location location, Border border);
}
