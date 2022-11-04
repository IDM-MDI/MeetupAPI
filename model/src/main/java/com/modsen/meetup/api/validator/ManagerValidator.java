package com.modsen.meetup.api.validator;

import com.modsen.meetup.api.dto.ManagerDto;

import java.util.Objects;

import static com.modsen.meetup.api.util.ManagerUtil.getFullName;
import static com.modsen.meetup.api.validator.ObjectValidator.isStringEmpty;

public class ManagerValidator {
    private ManagerValidator() {}

    public static boolean isManagerValid(ManagerDto manager) {
        if(Objects.isNull(manager)) {
            return false;
        }
        String fullName = getFullName(manager);
        return !isStringEmpty(fullName) && fullName.length() < 155;
    }
}
