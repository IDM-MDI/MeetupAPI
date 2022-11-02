package com.modsen.meetup.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class PaginationInfo {
    private long page = 0;
    private long size = 10;
    private String filter = "";
    private String sort = "id";
    private String direction = "asc";
}
