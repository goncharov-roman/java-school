package org.roman;

import org.roman.proxy.ConcurrentCacheHandler;
import org.roman.service.Service;
import org.roman.service.ServiceImpl;

public class Main {

    public static void main(String[] args) {
        Service service = new ServiceImpl();
        Service proxyService = ConcurrentCacheHandler.proxyFactory(service);

        Thread t1 = new Thread(() -> proxyService.run("s", 1.0));
        Thread t2 = new Thread(() -> proxyService.run("s", 1.0));
        Thread t3 = new Thread(() -> proxyService.run("t", 2.0));
        Thread t4 = new Thread(() -> proxyService.run("s", 1.0));
        t1.start();
        t2.start();
        t3.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t4.start();
    }
}
