package com.modsen.meetup.api.exception;

public enum ModelExceptionCode {
    MODEL_MAPPER_EXCEPTION(1, "ERROR WHILE MAPPING MODEL");
    ModelExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    private int code;
    private String message;

    @Override
    public String toString() {
        return "ModelExceptionCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
