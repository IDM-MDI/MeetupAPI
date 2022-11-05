package com.modsen.meetup.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@ToString
public class PaginationInfo {
    private long page;
    private long size;
    private String filter;
    private String sort;
    private String direction;
}
