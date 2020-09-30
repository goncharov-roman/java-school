package org.roman.proxy;

import org.roman.annotation.Cache;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.ClassLoader.getSystemClassLoader;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

public class CacheInvocationHandler implements InvocationHandler {

    private final Object delegate;

    private Map<List<Object>, Object> cachedResults;

    private Map<List<Object>, Object> fileCachedResults;

    private final String fileName = "/Users/roman/Coding/cache.txt";

    public CacheInvocationHandler(Object delegate) {
        this.delegate = delegate;
        this.cachedResults = new HashMap<>();
        this.fileCachedResults = new HashMap<>();
    }

    public static <T> T proxyFactory(Object delegate) {
        return (T) Proxy.newProxyInstance(getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CacheInvocationHandler(delegate)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> argList = new ArrayList<>(asList(method.getName()));
        stream(args).forEach(argList::add);

        if (method.isAnnotationPresent(Cache.class)) {
            Cache annotation = method.getAnnotation(Cache.class);
            if (annotation.cacheType().equals(CacheType.MEMORY) && cachedResults.containsKey(argList)) {
                System.out.println("Get from memory. Method signature: " + argList.toString() + ". Cache: " + cachedResults.toString());
                return cachedResults.get(argList);
            } else if (annotation.cacheType().equals(CacheType.FILE)) {
                fileCachedResults = deserialize(fileName);
                if (fileCachedResults.containsKey(argList)) {
                    System.out.println("Get from file. Method signature: " + argList.toString() + ". Cache: " + fileCachedResults.toString());
                    return fileCachedResults.get(argList);
                }
            }
        }

        Object result = method.invoke(delegate, args);
        if (method.isAnnotationPresent(Cache.class)) {
            saveInCache(method, argList, result);
        }
        return result;
    }

    private void saveInCache(Method method, List<Object> argList, Object result) throws IOException {
        Cache annotation = method.getAnnotation(Cache.class);
        if (annotation.cacheType().equals(CacheType.MEMORY)) {
            cachedResults.put(argList, result);
            System.out.println("Put value in memory. Method signature: " + argList.toString() + ". Result: " + result.toString());
        } else if (annotation.cacheType().equals(CacheType.FILE)) {
            fileCachedResults.put(argList, result);
            serialize(fileName, fileCachedResults);
            System.out.println("Put value in file. Method signature: " + argList.toString() + ". Result: " + result.toString());
        }
    }

    private void serialize(String fileName, Map<List<Object>, Object> cached) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(cached);
        }
    }

    private Map<List<Object>, Object> deserialize(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fis)) {
            return (Map<List<Object>, Object>) in.readObject();
        }
    }
}
