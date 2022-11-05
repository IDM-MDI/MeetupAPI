package com.modsen.meetup.api.util;

import com.modsen.meetup.api.dto.ManagerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.modsen.meetup.api.util.ManagerUtil.getFullName;

class ManagerUtilTest {
    private static final String TEST = "TEST";
    private static final String CORRECT_FULL_NAME = "TEST TEST TEST";
    private static final ManagerDto EXPECTED_MANAGER =
            ManagerDto.builder()
                    .name(TEST)
                    .surname(TEST)
                    .lastname(TEST)
                    .build();

    @Test
    void getFullNameShouldCorrectFull() {
        Assertions.assertEquals(CORRECT_FULL_NAME,getFullName(EXPECTED_MANAGER));
    }

    @Test
    void getFullNameShouldEmpty() {
        Assertions.assertTrue(getFullName(ManagerDto.builder().build()).isEmpty());
    }

    @Test
    void getFullNameShouldCorrectNotFull() {
        ManagerDto managerDto = ManagerDto.builder()
                .name(TEST)
                .lastname(TEST)
                .build();
        String expected = "TEST TEST";
        Assertions.assertEquals(expected,getFullName(managerDto));
    }
}