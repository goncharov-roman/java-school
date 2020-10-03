package org.roman.cipher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CipherUtilsTest {

    @Test
    void testEncrypt() {
        String cryptogram = CipherUtils.encrypt("Hello, world!");
        System.out.println(cryptogram);

        assertTrue(cryptogram.length() > 0);
    }
}
