package com.modsen.meetup.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationInfo {
    private long page;
    private long size;
    private String filter;
    private String sort;
    private String direction;
}
