package org.roman.proxy;

import org.roman.annotation.Metric;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Duration;
import java.time.Instant;

import static java.lang.ClassLoader.getSystemClassLoader;

public class PerformanceInvocationHandler implements InvocationHandler {

    private final Object delegate;

    public PerformanceInvocationHandler(Object delegate) {
        this.delegate = delegate;
    }

    public static <T> T proxyFactory(Object delegate) {
        return (T) Proxy.newProxyInstance(getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new PerformanceInvocationHandler(delegate)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Metric.class)) {
            Instant start = Instant.now();
            Object result = method.invoke(delegate, args);
            Instant end = Instant.now();
            System.out.println("Method work time: " + Duration.between(start, end).toNanos());

            return result;
        }
        return method.invoke(delegate, args);
    }
}
