package com.bookapp.utils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ClassUtils {
    public static boolean isClassContainsProperty(Class<?> entity, String property) {
        Field[] fields = entity.getFields();
        return Arrays.stream(fields).anyMatch(field -> field.getName().equals(property));
    }
}
