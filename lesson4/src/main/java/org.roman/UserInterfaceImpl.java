package org.roman;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для вывода информации пользователю
 */
public class UserInterfaceImpl implements UserInterface {

    private final List<String> output;

    public UserInterfaceImpl() {
        this.output = new ArrayList<>();
    }

    @Override
    public List<String> getOutput() {
        return output;
    }

    @Override
    public void addToOutput(String info) {
        output.add(info);
    }
}
