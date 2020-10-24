package org.roman.tractor.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandFactory {

    static Map<String, Command> commandMap = new HashMap<>();
    static {
        commandMap.put("F", new ForwardCommand());
        commandMap.put("T", new TurnCommand());
    }

    public static Optional<Command> getCommand(String command) {
        return Optional.ofNullable(commandMap.get(command));
    }
}
