package org.roman;

import java.util.List;

public interface Terminal {

    void checkBalance();

    void getCash(Integer sum);

    void putCash(Integer sum);

    void enterSymbol(Character symbol);

    List<String> getOutput();

    void endSession();
}
