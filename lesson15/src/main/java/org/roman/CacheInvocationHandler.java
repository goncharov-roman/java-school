package org.roman;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.List;

import static java.lang.ClassLoader.getSystemClassLoader;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CacheInvocationHandler implements InvocationHandler {

    private final Object delegate;

    public CacheInvocationHandler(Object delegate) {
        this.delegate = delegate;
    }

    public static <T> T proxyFactory(Object delegate) {
        return (T) Proxy.newProxyInstance(getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CacheInvocationHandler(delegate)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        int arg = (int) args[0];

        if (method.isAnnotationPresent(Cachable.class) && method.getAnnotation(Cachable.class).value().equals(H2DB.class)) {
            try (Connection connection = DriverManager.getConnection("jdbc:h2:./cachedb", "sa", "");
                 PreparedStatement statementSelect = connection.prepareStatement("SELECT result FROM cache_table WHERE arg <= ?");
                 PreparedStatement statementInsert = connection.prepareStatement("INSERT INTO cache_table (arg, result) VALUES (?, ?)")) {
                statementSelect.setInt(1, arg);
                ResultSet resultSet = statementSelect.executeQuery();
                if (resultSet.next()) {
                    String result = resultSet.getString(2);
                    return stream(result.split(",")).map(Integer::parseInt).collect(toList());
                } else {
                    List<Integer> result = (List<Integer>) method.invoke(delegate, args);
                    String joined = result.stream().map(Object::toString).collect(joining(","));
                    statementInsert.setInt(1, arg);
                    statementInsert.setString(2, joined);
                    statementInsert.execute();
                    return result;
                }
            }
        }

        return method.invoke(delegate, args);
    }
}
