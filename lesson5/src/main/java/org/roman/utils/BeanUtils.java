package org.roman.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Objects.isNull;

public class BeanUtils {

    public static void assign(Object from, Object to) throws InvocationTargetException, IllegalAccessException {
        List<Method> gettersList = stream(from.getClass().getMethods())
                .filter(method -> method.getName().startsWith("get"))
                .collect(Collectors.toList());

        Map<String, Method> setterNameToMethod = new HashMap<>();

        stream(to.getClass().getMethods())
                .forEach(method -> {
                    if (method.getName().startsWith("set")) {
                        setterNameToMethod.put(method.getName(), method);
                    }
                });

        for (Method getter : gettersList) {
            String setterName = "s" + getter.getName().substring(1);
            if (setterNameToMethod.containsKey(setterName)) {
                Method setter = setterNameToMethod.get(setterName);
                Class<?> getterType = getter.getReturnType();
                Class<?> setterType = setter.getParameterTypes()[0];

                if (isCompatible(getterType, setterType)) {
                    Object value = getter.invoke(from);
                    setter.invoke(to, value);
                }
            }
        }
    }

    public static boolean isCompatible(Class<?> from, Class<?> to) {
        while (!from.equals(to)) {
            from = from.getSuperclass();
            if (isNull(from)) {
                return false;
            }
        }

        return true;
    }
}
