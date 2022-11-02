package com.modsen.meetup.api.util;

import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.validator.PaginationValidator;

import java.util.Objects;

public class PaginationUtil {
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 5;
    private static final String DEFAULT_SORT = "id";
    private static final String DEFAULT_DIRECTION = "ASC";
    private static final String DEFAULT_FILTER = "";

    private static final PaginationInfo DEFAULT_PAGINATION
            = PaginationInfo
            .builder()
            .page(DEFAULT_PAGE)
            .size(DEFAULT_SIZE)
            .sort(DEFAULT_SORT)
            .filter(DEFAULT_FILTER)
            .direction(DEFAULT_DIRECTION)
            .build();
    private PaginationUtil(){}

    public static PaginationInfo paginationFilter(PaginationInfo info) {
        if(Objects.isNull(info)) {
            return DEFAULT_PAGINATION;
        }
        return fillEmptyField(info);
    }
    private static PaginationInfo fillEmptyField(PaginationInfo info) {
        return PaginationInfo
                .builder()
                .page(PaginationValidator.isPaginationNumberFieldNotValid(info.getPage()) ? DEFAULT_PAGE : info.getPage())
                .size(PaginationValidator.isPaginationNumberFieldNotValid(info.getSize()) ? DEFAULT_SIZE : info.getSize())
                .sort(PaginationValidator.isPaginationStringFieldEmpty(info.getSort()) ? DEFAULT_SORT : info.getSort())
                .filter(PaginationValidator.isPaginationStringFieldEmpty(info.getFilter()) ? DEFAULT_FILTER : info.getFilter())
                .direction(PaginationValidator.isPaginationStringFieldEmpty(info.getDirection()) ? DEFAULT_DIRECTION : info.getDirection().toUpperCase())
                .build();
    }
}
