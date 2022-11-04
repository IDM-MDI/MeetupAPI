package com.modsen.meetup.api.validator;

import java.util.Objects;

public class PaginationValidator {
    private PaginationValidator() {}

    public static boolean isPaginationStringFieldEmpty(String field) {
        return Objects.isNull(field) || field.isEmpty();
    }
    public static boolean isPaginationNumberFieldNotValid(long field) {
        return field < 0;
    }
}
