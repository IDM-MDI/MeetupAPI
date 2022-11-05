package com.modsen.meetup.api.util.impl;

import com.modsen.meetup.api.dto.VenueDto;
import com.modsen.meetup.api.entity.Venue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class VenueModelMapperTest {

    private static final String TEST = "TEST";
    private static final Venue ENTITY =
            Venue.builder()
                    .id(1)
                    .name(TEST)
                    .build();
    private static final VenueDto DTO =
            VenueDto.builder()
                    .id(1)
                    .name(TEST)
                    .build();
    private static final List<Venue> ENTITY_LIST = List.of(ENTITY);

    private static final List<VenueDto> DTO_LIST = List.of(DTO);
    private final VenueModelMapper mapper;

    VenueModelMapperTest() {
        this.mapper = new VenueModelMapper();
    }

    @Test
    void toEntityShouldCorrect() {
        Venue actual = mapper.toEntity(DTO);
        Assertions.assertEquals(ENTITY,actual);
    }

    @Test
    void toDtoShouldCorrect() {
        VenueDto actual = mapper.toDto(ENTITY);
        Assertions.assertEquals(DTO,actual);
    }

    @Test
    void toEntityListShouldCorrect() {
        List<Venue> actual = mapper.toEntityList(DTO_LIST);
        Assertions.assertEquals(ENTITY_LIST,actual);
    }

    @Test
    void toDtoListShouldCorrect() {
        List<VenueDto> actual = mapper.toDtoList(ENTITY_LIST);
        Assertions.assertEquals(DTO_LIST,actual);
    }
    @Test
    void toEntityShouldIncorrectByNull() {
        Assertions.assertNull(mapper.toEntity(null));
    }

    @Test
    void toDtoShouldIncorrectByNull() {
        Assertions.assertNull(mapper.toDto(null));
    }

    @Test
    void toEntityListShouldIncorrectByNull() {
        Assertions.assertTrue(mapper.toEntityList(null).isEmpty());
    }

    @Test
    void toDtoListShouldIncorrectByNull() {
        Assertions.assertTrue(mapper.toDtoList(null).isEmpty());
    }
}