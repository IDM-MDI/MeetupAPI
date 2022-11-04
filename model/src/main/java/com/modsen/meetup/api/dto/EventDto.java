package com.modsen.meetup.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode
public class EventDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private ManagerDto manager;
    private String topic;
    private String description;
    private LocalDateTime date;
    private VenueDto venue;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;
}
