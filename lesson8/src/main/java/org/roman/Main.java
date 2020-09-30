package org.roman;

import org.roman.proxy.CacheInvocationHandler;
import org.roman.service.Service;
import org.roman.service.ServiceImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Service service = new ServiceImpl();
        Service proxyService = CacheInvocationHandler.proxyFactory(service);

        try {
            initializeFileCache();
        } catch (IOException e) {
            e.printStackTrace();
        }

        proxyService.run("s", 1.0);
        proxyService.run("s", 1.0);

        proxyService.work("item");
        proxyService.work("work");
        proxyService.work("item");
    }

    private static void initializeFileCache() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("/Users/roman/Coding/cache.txt");
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(new HashMap<List<Object>, Object>());
        }
    }
}
