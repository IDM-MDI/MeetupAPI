package com.modsen.meetup.api.validator;

import com.modsen.meetup.api.dto.ManagerDto;

import static com.modsen.meetup.api.validator.ObjectValidator.isStringEmpty;

public class ManagerValidator {
    private static final String SPACE = " ";
    private ManagerValidator() {}

    public static boolean isManagerValid(ManagerDto manager) {
        String fullName = manager.getName() + SPACE +
                manager.getSurname() + SPACE +
                manager.getLastname();
        return isStringEmpty(fullName) && fullName.length() < 155;
    }
}
