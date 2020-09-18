package org.roman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TerminalImplTest {

    private Terminal terminal;

    @BeforeEach
    public void setUp() {
        TerminalServer terminalServer = new TerminalServerImpl(1000);
        PinValidator pinValidator = new PinValidatorImpl("1234");
        UserInterface userInterface = new UserInterfaceImpl();

        terminal = new TerminalImpl(terminalServer, pinValidator, userInterface);
    }

    /**
     * General flow test
     */
    @Test
    public void testTerminalImplGoodPin() {
        terminal.checkBalance();

        terminal.enterSymbol('1');
        terminal.enterSymbol('2');
        terminal.enterSymbol('3');
        terminal.enterSymbol('4');

        terminal.getCash(100);
        terminal.putCash(500);
        terminal.getCash(1500);

        terminal.checkBalance();

        terminal.endSession();

        List<String> output = terminal.getOutput();
        assertEquals("You has no access!", output.get(0));
        assertEquals("Access granted", output.get(1));
        assertEquals("New balance is 900", output.get(2));
        assertEquals("New balance is 1400", output.get(3));
        assertEquals("Insufficient funds!", output.get(4));
        assertEquals("Balance is 1400", output.get(5));
        assertEquals("Good day!", output.get(6));
    }

    /**
     * Typos in PIN & non divisible by 100 sums test
     */
    @Test
    public void testTerminalImplCharsAndNonDivisible() {
        terminal.enterSymbol('1');
        terminal.enterSymbol('2');
        terminal.enterSymbol('+');
        terminal.enterSymbol('3');
        terminal.enterSymbol('#');
        terminal.enterSymbol('4');

        terminal.putCash(150);
        terminal.getCash(50);

        List<String> output = terminal.getOutput();
        assertEquals("+ is not digit! Enter digit", output.get(0));
        assertEquals("# is not digit! Enter digit", output.get(1));
        assertEquals("Access granted", output.get(2));
        assertEquals("150 is non divisible by 100!", output.get(3));
        assertEquals("50 is non divisible by 100!", output.get(4));

    }

    /**
     * Locked account test
     * @throws InterruptedException Thread sleep exception
     */
    @Test
    public void testTerminalImplBadPin() throws InterruptedException {
        terminal.enterSymbol('1');
        terminal.enterSymbol('2');
        terminal.enterSymbol('3');
        terminal.enterSymbol('3');

        terminal.enterSymbol('1');
        terminal.enterSymbol('2');
        terminal.enterSymbol('3');
        terminal.enterSymbol('2');

        terminal.enterSymbol('1');
        terminal.enterSymbol('2');
        terminal.enterSymbol('3');
        terminal.enterSymbol('5');

        Thread.sleep(2000);

        terminal.enterSymbol('1');
        terminal.enterSymbol('2');
        terminal.enterSymbol('3');
        terminal.enterSymbol('6');

        Thread.sleep(8000);

        terminal.enterSymbol('1');
        terminal.enterSymbol('2');
        terminal.enterSymbol('3');
        terminal.enterSymbol('4');

        terminal.checkBalance();

        terminal.endSession();

        List<String> output = terminal.getOutput();
        assertEquals("PIN is wrong! Try again", output.get(0));
        assertEquals("PIN is wrong! Try again", output.get(1));
        assertTrue(output.get(2).startsWith("Account is blocked on 10 sec till"));
        assertTrue(output.get(3).startsWith("Account will be blocked"));
        assertEquals("Access granted", output.get(4));
        assertEquals("Balance is 1000", output.get(5));
        assertEquals("Good day!", output.get(6));
    }
}
