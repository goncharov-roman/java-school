package org.roman;

import org.roman.proxy.CachedInvocationHandler;
import org.roman.proxy.PerformanceInvocationHandler;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        Calculator proxyCalc = CachedInvocationHandler.proxyFactory(calculator);
        Calculator performanceCalc = PerformanceInvocationHandler.proxyFactory(calculator);

        run(proxyCalc);
        run(performanceCalc);
    }

    private static void run(Calculator calculator) {
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(10));
        System.out.println(calculator.calc(8));
        System.out.println(calculator.calc(8));
        System.out.println(calculator.calc(8));
        System.out.println(calculator.calc(10));
        System.out.println(calculator.calc(1));
    }
}
