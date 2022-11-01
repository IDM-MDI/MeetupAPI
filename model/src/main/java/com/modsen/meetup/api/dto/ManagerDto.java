package com.modsen.meetup.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ManagerDto {
    private String name;
    private String surname;
    private String lastname;
}
