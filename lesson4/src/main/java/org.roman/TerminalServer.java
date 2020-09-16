package org.roman;

import org.roman.exception.NonDivisibleBy100Exception;
import org.roman.exception.NotEnoughMoneyException;

/**
 * ATM backend implementation
 */
public class TerminalServer {

    private Integer accountBalance;

    public TerminalServer(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Integer checkBalance() {
        return accountBalance;
    }

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

    public Integer putCash(Integer sum) throws NonDivisibleBy100Exception {
        if (sum % 100 != 0) {
            throw new NonDivisibleBy100Exception(sum + " is non divisible by 100!");
        }
        accountBalance += sum;
        return accountBalance;
    }
}
