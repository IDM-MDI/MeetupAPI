package com.modsen.meetup.api.exception;

public enum PersistenceCode {
    ERROR_WHILE_SAVE(100,"Something going wrong while saving"),
    ERROR_WHILE_DELETE(101,"Something going wrong while delete"),
    ERROR_WHILE_UPDATE(101,"Something going wrong while update"),

    ;

    private final int code;
    private final String message;

    PersistenceCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "PersistenceCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
