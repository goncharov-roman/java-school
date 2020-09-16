package org.roman;

import org.roman.exception.AccountBlockException;
import org.roman.exception.AccountIsLockedException;
import org.roman.exception.NonDivisibleBy100Exception;
import org.roman.exception.NotEnoughMoneyException;

import java.util.List;

/**
 * ATM terminal implementation
 */
public class TerminalImpl implements Terminal {

    private final TerminalServer server;

    private final PinValidator pinValidator;

    private final UserInterface userInterface;

    private String pin;

    private Boolean hasAccess;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator, UserInterface userInterface) {
        this.server = server;
        this.pinValidator = pinValidator;
        this.userInterface = userInterface;
        this.pin = "";
        this.hasAccess = false;
    }

    @Override
    public void checkBalance() {
        if (!hasAccess) {
            userInterface.addToOutput("You has no access!");
            return;
        }
        Integer balance = server.checkBalance();
        userInterface.addToOutput("Balance is " + balance);
    }

    @Override
    public void getCash(Integer sum) {
        if (!hasAccess) {
            userInterface.addToOutput("You has no access!");
            return;
        }
        try {
            Integer newBalance = server.getCash(sum);
            userInterface.addToOutput("New balance is " + newBalance);
        } catch (NotEnoughMoneyException | NonDivisibleBy100Exception e) {
            userInterface.addToOutput(e.getMessage());
        }
    }

    @Override
    public void putCash(Integer sum) {
        if (!hasAccess) {
            userInterface.addToOutput("You has no access!");
            return;
        }
        try {
            Integer newBalance = server.putCash(sum);
            userInterface.addToOutput("New balance is " + newBalance);
        } catch (NonDivisibleBy100Exception e) {
            userInterface.addToOutput(e.getMessage());
        }
    }

    @Override
    public void enterSymbol(Character symbol) {
        if (!Character.isDigit(symbol)) {
            userInterface.addToOutput(symbol + " is not digit! Enter digit");
            return;
        }
        pin += symbol;

        if (pin.length() == 4) {
            try {
                Boolean isValid = pinValidator.validate(pin);
                if (isValid) {
                    hasAccess = true;
                    userInterface.addToOutput("Access granted");
                } else {
                    userInterface.addToOutput("PIN is wrong! Try again");
                }
                pin = "";
            } catch (AccountBlockException | AccountIsLockedException e) {
                pin = "";
                userInterface.addToOutput(e.getMessage());
            }
        }
    }

    @Override
    public List<String> getOutput() {
        return userInterface.getOutput();
    }

    @Override
    public void endSession() {
        hasAccess = false;
        userInterface.addToOutput("Good day!");
    }
}
