package com.modsen.meetup.api.exception;

public enum ServiceExceptionCode {
    ENTITY_NOT_FOUND(1000,"Expected entity was not found"),
    ENTITY_NOT_VALID(1001,"Entity not valid as required"),
    ENTITY_ALREADY_EXIST(1002, "Entity already exist");

    private int code;
    private String message;
    ServiceExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ServiceExceptionCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
