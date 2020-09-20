package org.roman.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static java.lang.ClassLoader.getSystemClassLoader;

public class CachedInvocationHandler implements InvocationHandler {

    private final Object delegate;

    private final Map<Object, Object> cachedResults;

    public CachedInvocationHandler(Object delegate) {
        this.delegate = delegate;
        this.cachedResults = new HashMap<>();
    }

    public static <T> T proxyFactory(Object delegate) {
        return (T) Proxy.newProxyInstance(getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CachedInvocationHandler(delegate)
        );
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object argument = args[0];
        if (cachedResults.containsKey(argument)) {
            return cachedResults.get(argument);
        }
        Object result = method.invoke(delegate, args);
        cachedResults.put(argument, result);
        return result;
    }
}
