package com.modsen.meetup.api.util;

import com.modsen.meetup.api.dto.ResponseStatus;

public class ResponseStatusUtil {
    private static final int OK_CODE = 200;
    private static final int CREATED_CODE = 201;

    private static final String SUCCESSFULLY_SAVED = " was successfully saved";
    private static final String SUCCESSFULLY_UPDATED = " was successfully updated";
    private static final String SUCCESSFULLY_DELETED = " was successfully deleted";
    private static final String PAGE_SUCCESSFULLY_FOUND = " page was successfully found";
    private static final String PAGE_NOT_FOUND = " page was not found";
    private static final String BY_ID_SUCCESSFULLY_FOUND = " by id was successfully found";
    private ResponseStatusUtil() {}

    public static ResponseStatus saveResponse(String entityName) {
        return ResponseStatus.builder()
                .code(CREATED_CODE)
                .message(entityName + SUCCESSFULLY_SAVED)
                .build();
    }
    public static ResponseStatus updateResponse(String entityName) {
        return ResponseStatus.builder()
                .code(CREATED_CODE)
                .message(entityName + SUCCESSFULLY_UPDATED)
                .build();
    }

    public static ResponseStatus deleteResponse(String entityName) {
        return ResponseStatus.builder()
                .code(OK_CODE)
                .message(entityName + SUCCESSFULLY_DELETED)
                .build();
    }
    public static ResponseStatus byIdFoundResponse(String entityName) {
        return ResponseStatus.builder()
                .code(OK_CODE)
                .message(entityName + BY_ID_SUCCESSFULLY_FOUND)
                .build();
    }

    public static ResponseStatus pageFoundResponse(String entityName) {
        return ResponseStatus.builder()
                .code(OK_CODE)
                .message(entityName + PAGE_SUCCESSFULLY_FOUND)
                .build();
    }

    public static ResponseStatus pageNotFoundResponse(String entityName) {
        return ResponseStatus.builder()
                .code(OK_CODE)
                .message(entityName + PAGE_NOT_FOUND)
                .build();
    }
}
