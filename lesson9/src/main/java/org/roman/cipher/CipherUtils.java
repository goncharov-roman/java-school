package org.roman.cipher;

public class CipherUtils {

    private CipherUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String encrypt(String text) {
        StringBuilder encrypted = new StringBuilder();
        text.chars().forEach(c -> encrypted.append(c ^ getGammaElement()));

        return encrypted.toString();
    }

    private static int getGammaElement() {
        return (int) (Math.random() * 10);
    }
}
