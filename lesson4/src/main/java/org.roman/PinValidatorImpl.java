package org.roman;

import org.roman.exception.AccountBlockException;
import org.roman.exception.AccountIsLockedException;

import java.time.Duration;
import java.time.LocalTime;

/**
 * PIN validator implementation
 */
public class PinValidatorImpl implements PinValidator {

    private final String validPin;

    private LocalTime blockUntil;
    private Integer tryCount;

    public PinValidatorImpl(String validPin) {
        this.validPin = validPin;
        this.tryCount = 0;
        this.blockUntil = LocalTime.now();
    }

    @Override
    public Boolean validate(String pin) throws AccountBlockException, AccountIsLockedException {
        if (LocalTime.now().isBefore(blockUntil)) {
            throw new AccountIsLockedException("Account will be blocked " + Duration.between(LocalTime.now(), blockUntil).getSeconds() + " sec");
        }

        if (!pin.equals(validPin) && tryCount == 2) {
            blockUntil = LocalTime.now().plusSeconds(10);
            tryCount = 0;
            throw new AccountBlockException("Account is blocked on 10 sec till " + blockUntil);
        }

        if (!pin.equals(validPin)) {
            tryCount++;
            return false;
        }

        return true;
    }
}
