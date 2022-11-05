package com.modsen.meetup.api.util;

import com.modsen.meetup.api.dto.ResponseStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseStatusUtilTest {
    private static final String CLASSNAME = "event ";
    private static final int OK_CODE = 200;
    private static final int CREATED_CODE = 201;

    private static final String SUCCESSFULLY_SAVED = " was successfully saved";
    private static final String SUCCESSFULLY_UPDATED = " was successfully updated";
    private static final String SUCCESSFULLY_DELETED = " was successfully deleted";
    private static final String PAGE_SUCCESSFULLY_FOUND = " page was successfully found";
    private static final String PAGE_NOT_FOUND = " page was not found";
    private static final String BY_ID_SUCCESSFULLY_FOUND = " by id was successfully found";

    @Test
    void saveResponse() {
        ResponseStatus expected = ResponseStatus.builder()
                .code(CREATED_CODE)
                .message(CLASSNAME + SUCCESSFULLY_SAVED)
                .build();
        ResponseStatus actual = ResponseStatusUtil.saveResponse(CLASSNAME);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void updateResponse() {
        ResponseStatus expected = ResponseStatus.builder()
                .code(CREATED_CODE)
                .message(CLASSNAME + SUCCESSFULLY_UPDATED)
                .build();
        ResponseStatus actual = ResponseStatusUtil.updateResponse(CLASSNAME);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void deleteResponse() {
        ResponseStatus expected = ResponseStatus.builder()
                .code(OK_CODE)
                .message(CLASSNAME + SUCCESSFULLY_DELETED)
                .build();
        ResponseStatus actual = ResponseStatusUtil.deleteResponse(CLASSNAME);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void byIdFoundResponse() {
        ResponseStatus expected = ResponseStatus.builder()
                .code(OK_CODE)
                .message(CLASSNAME + BY_ID_SUCCESSFULLY_FOUND)
                .build();
        ResponseStatus actual = ResponseStatusUtil.byIdFoundResponse(CLASSNAME);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void pageFoundResponse() {
        ResponseStatus expected = ResponseStatus.builder()
                .code(OK_CODE)
                .message(CLASSNAME + PAGE_SUCCESSFULLY_FOUND)
                .build();
        ResponseStatus actual = ResponseStatusUtil.pageFoundResponse(CLASSNAME);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void pageNotFoundResponse() {
        ResponseStatus expected = ResponseStatus.builder()
                .code(OK_CODE)
                .message(CLASSNAME + PAGE_NOT_FOUND)
                .build();
        ResponseStatus actual = ResponseStatusUtil.pageNotFoundResponse(CLASSNAME);
        Assertions.assertEquals(expected,actual);
    }
}