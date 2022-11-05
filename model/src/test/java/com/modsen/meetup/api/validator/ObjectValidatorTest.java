package com.modsen.meetup.api.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectValidatorTest {
    private static final String EMPTY_STRING = "";
    private static final String TEST_STRING = "Test";
    @Test
    void isStringEmptyShouldFalse() {
        Assertions.assertFalse(ObjectValidator.isStringEmpty(TEST_STRING));
    }
    @Test
    void isStringEmptyShouldTrue() {
        Assertions.assertTrue(ObjectValidator.isStringEmpty(EMPTY_STRING));
    }
    @Test
    void isStringEmptyShouldTrueByNull() {
        Assertions.assertTrue(ObjectValidator.isStringEmpty(null));
    }

    @Test
    void isNumberEqLessZeroShouldTrue() {
        Assertions.assertTrue(ObjectValidator.isNumberEqLessZero(-1L));
    }

    @Test
    void isNumberEqLessZeroShouldFalse() {
        Assertions.assertFalse(ObjectValidator.isNumberEqLessZero(1L));
    }
}