package org.roman;

import org.roman.exception.NonDivisibleBy100Exception;
import org.roman.exception.NotEnoughMoneyException;

/**
 * ATM backend implementation
 */
public class TerminalServerImpl implements TerminalServer {

    private Integer accountBalance;

    public TerminalServerImpl(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public Integer checkBalance() {
        return accountBalance;
    }

    @Override
    public Integer getCash(Integer sum) throws NotEnoughMoneyException, NonDivisibleBy100Exception {
        if (sum % 100 != 0) {
            throw new NonDivisibleBy100Exception(sum + " is non divisible by 100!");
        }
        if (accountBalance >= sum) {
            accountBalance -= sum;
            return accountBalance;
        } else {
            throw new NotEnoughMoneyException("Insufficient funds!");
        }
    }

    @Override
    public Integer putCash(Integer sum) throws NonDivisibleBy100Exception {
        if (sum % 100 != 0) {
            throw new NonDivisibleBy100Exception(sum + " is non divisible by 100!");
        }
        accountBalance += sum;
        return accountBalance;
    }
}
