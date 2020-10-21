package org.roman.proxy;

import org.roman.annotation.Cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.ClassLoader.getSystemClassLoader;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

public class ConcurrentCacheHandler implements InvocationHandler {

    private final Object delegate;

    private final ConcurrentMap<List<Object>, Future<Object>> cachedResults;

    public ConcurrentCacheHandler(Object delegate) {
        this.delegate = delegate;
        this.cachedResults = new ConcurrentHashMap<>();
    }

    public static <T> T proxyFactory(Object delegate) {
        return (T) Proxy.newProxyInstance(getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new ConcurrentCacheHandler(delegate)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> argList = new ArrayList<>(asList(method.getName()));
        stream(args).forEach(argList::add);

        if (method.isAnnotationPresent(Cache.class)) {
            System.out.println(System.currentTimeMillis() + " " + Thread.currentThread() + " Args: " + argList);
            Future<Object> f = cachedResults.get(argList);
            if (f == null) {
                System.out.println(System.currentTimeMillis() + " " + Thread.currentThread() + " Cache not have value");
                Callable<Object> eval = () -> method.invoke(delegate, args);
                FutureTask<Object> ft = new FutureTask<>(eval);
                f = cachedResults.putIfAbsent(argList, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                    System.out.println(System.currentTimeMillis() + " " + Thread.currentThread() + " Task is running");
                }
            }
            try {
                System.out.println(System.currentTimeMillis() + " " + Thread.currentThread() +  " Will get");
                return f.get();
            } catch (CancellationException e) {
                cachedResults.remove(argList, f);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return method.invoke(delegate, args);
    }
}
