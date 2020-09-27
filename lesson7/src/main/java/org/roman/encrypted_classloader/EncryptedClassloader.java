package org.roman.encrypted_classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.Byte.parseByte;

public class EncryptedClassloader extends ClassLoader {

    private final String key;
    private final File dir;

    public EncryptedClassloader(ClassLoader parent, String key, File dir) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] loaded = loadClassFromFile(name);
        byte[] decrypted = decrypt(loaded, key);
        return defineClass(name, decrypted, 0, decrypted.length);
    }

    private byte[] loadClassFromFile(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                fileName.replace('.', File.separatorChar) + ".class");
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ((nextValue = inputStream.read()) != -1) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }

    private byte[] decrypt(byte[] b, String key) {
        byte[] decrypted = new byte[b.length];
        for (int i = 0; i < b.length; i++) {
            decrypted[i] = (byte) (b[i] - parseByte(key));
        }

        return decrypted;
    }
}
