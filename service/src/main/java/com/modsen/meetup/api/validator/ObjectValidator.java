package com.modsen.meetup.api.validator;

import java.util.Objects;

public class ObjectValidator {
    private ObjectValidator() {}

    public static boolean isStringEmpty(String string) {
        return Objects.isNull(string) || string.isBlank();
    }

    public static boolean isNumberEqLessZero(long number) {
        return number <= 0;
    }
}
