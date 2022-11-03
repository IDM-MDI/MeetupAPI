package com.modsen.meetup.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VenueDto {
    private long id;
    private String name;
}
