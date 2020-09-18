package org.roman;

import org.roman.exception.AccountBlockException;
import org.roman.exception.AccountIsLockedException;

public interface PinValidator {

    Boolean validate(String pin) throws AccountBlockException, AccountIsLockedException;
}
