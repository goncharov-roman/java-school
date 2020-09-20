package org.roman;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Objects.isNull;

public class ReflectionUtils {

    public static List<String> getAllMethods(Object object) {
        List<String> result = new ArrayList<>();

        Class<?> clazz = object.getClass();
        while (!isNull(clazz)) {
            Method[] methods = clazz.getDeclaredMethods();
            stream(methods).forEach(method -> result.add(method.getName()));
            clazz = clazz.getSuperclass();
        }

        return result;
    }

    public static List<String> getAllGetters(Object object) {
        return getAllMethods(object).stream()
                .filter(method -> method.startsWith("get"))
                .collect(Collectors.toList());
    }

    public static boolean areConstantsValid(Object object) throws IllegalAccessException {
        List<Field> fields = stream(object.getClass().getDeclaredFields())
                .filter(field -> {
                    field.setAccessible(true);
                    return (field.getModifiers() & Modifier.FINAL) > 0
                            && field.getType().equals(String.class);
                })
                .collect(Collectors.toList());

        for (Field field : fields) {
            String value = (String) field.get(object);
            if (!field.getName().equals(value)) {
                return false;
            }
        }

        return true;
    }
}
