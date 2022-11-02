package com.modsen.meetup.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseStatus {
    private int code;
    private String message;
}
