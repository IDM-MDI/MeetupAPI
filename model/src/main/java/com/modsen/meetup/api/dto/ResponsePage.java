package com.modsen.meetup.api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponsePage <T> {
    private List<T> data;
    private PaginationInfo paginationInfo;
}
