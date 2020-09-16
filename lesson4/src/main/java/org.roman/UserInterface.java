package org.roman;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для вывода информации пользователю
 */
public class UserInterface {

    private final List<String> output;

    public UserInterface() {
        this.output = new ArrayList<>();
    }

    public List<String> getOutput() {
        return output;
    }

    public void addToOutput(String info) {
        output.add(info);
    }
}
