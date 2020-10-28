package org.roman;

public class Main {

    public static void main(String[] args) {

        Calculator calculator = new CalculatorImpl();
        Calculator calcProxy = CacheInvocationHandler.proxyFactory(calculator);

        calcProxy.fibonacci(5);
        calcProxy.fibonacci(5);
    }
}
