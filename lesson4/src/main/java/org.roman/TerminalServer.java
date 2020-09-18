package org.roman;

import org.roman.exception.NonDivisibleBy100Exception;
import org.roman.exception.NotEnoughMoneyException;

public interface TerminalServer {

    Integer checkBalance();

    Integer getCash(Integer sum) throws NotEnoughMoneyException, NonDivisibleBy100Exception;

    Integer putCash(Integer sum) throws NonDivisibleBy100Exception;
}
