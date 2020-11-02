package org.roman;

import org.roman.proxy.CacheInvocationHandler;
import org.roman.service.Calculator;
import org.roman.service.CalculatorImpl;

public class Main {

    public static void main(String[] args) {

        Calculator calculator = new CalculatorImpl();
        Calculator calcProxy = CacheInvocationHandler.proxyFactory(calculator);

        System.out.println(calcProxy.fibonacci(5));
        System.out.println(calcProxy.fibonacci(5));
        System.out.println(calcProxy.fibonacci(6));
        System.out.println(calcProxy.fibonacci(7));
        System.out.println(calcProxy.fibonacci(25));
        System.out.println(calcProxy.fibonacci(6));
        System.out.println(calcProxy.fibonacci(25));
    }
}
