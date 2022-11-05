package com.modsen.meetup.api.util;

import com.modsen.meetup.api.dto.ManagerDto;

import java.util.Objects;

import static com.modsen.meetup.api.validator.ObjectValidator.isStringEmpty;

public class ManagerUtil {
    private static final String SPACE = " ";

    private ManagerUtil() {}

    public static String getFullName(ManagerDto manager) {
        if(Objects.isNull(manager) ||
                (isStringEmpty(manager.getName()) &&
                        isStringEmpty(manager.getSurname()) &&
                        isStringEmpty(manager.getLastname()))) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        if(!isStringEmpty(manager.getName())) {
            builder.append(manager.getName())
                    .append(SPACE);
        }
        if(!isStringEmpty(manager.getSurname())) {
            builder.append(manager.getSurname())
                    .append(SPACE);
        }
        if(!isStringEmpty(manager.getLastname())) {
            builder.append(manager.getLastname());
        }
        return builder.toString().trim();
    }
}
