package com.modsen.meetup.api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDto {
    private ManagerDto manager;
    private String topic;
    private String description;
    private LocalDateTime date;
    private VenueDto venue;
}
