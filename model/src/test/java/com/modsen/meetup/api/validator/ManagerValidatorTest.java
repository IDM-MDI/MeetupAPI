package com.modsen.meetup.api.validator;

import com.modsen.meetup.api.dto.ManagerDto;
import com.modsen.meetup.api.util.ManagerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManagerValidatorTest {
    private static final ManagerDto EMPTY_MANAGER = ManagerDto.builder().build();
    private static final String EMPTY_STRING = "";
    private static final String FULL_NAME_STRING = "Test Test Test";
    @Test
    void isManagerValidShouldTrue() {
        try (MockedStatic<ManagerUtil> utilMockedStatic = mockStatic(ManagerUtil.class)) {
            when(ManagerUtil.getFullName(EMPTY_MANAGER))
                    .thenReturn(FULL_NAME_STRING);
            Assertions.assertTrue(ManagerValidator.isManagerValid(EMPTY_MANAGER));
        }
    }
    @Test
    void isManagerValidShouldFalse() {
        try (MockedStatic<ManagerUtil> utilMockedStatic = mockStatic(ManagerUtil.class)) {
            when(ManagerUtil.getFullName(EMPTY_MANAGER))
                    .thenReturn(EMPTY_STRING);
            Assertions.assertFalse(ManagerValidator.isManagerValid(EMPTY_MANAGER));
        }
    }
    @Test
    void isManagerValidShouldFalseByNull() {
        Assertions.assertFalse(ManagerValidator.isManagerValid(null));
    }
}