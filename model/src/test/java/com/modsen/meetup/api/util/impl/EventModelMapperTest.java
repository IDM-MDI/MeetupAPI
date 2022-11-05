package com.modsen.meetup.api.util.impl;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.entity.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.modsen.meetup.api.entity.EntityStatus.ACTIVE;

class EventModelMapperTest {
    private static final String TEST = "TEST";
    private static final Event ENTITY =
            Event.builder()
                    .id(1)
                    .topic(TEST)
                    .description(TEST + TEST + TEST)
                    .venue(null)
                    .manager(null)
                    .date(LocalDateTime.of(2012,12,12,12,12))
                    .status(ACTIVE.name())
                    .build();
    private static final EventDto DTO =
            EventDto.builder()
                    .id(1)
                    .topic(TEST)
                    .description(TEST + TEST + TEST)
                    .venue(null)
                    .manager(null)
                    .date(LocalDateTime.of(2012,12,12,12,12))
                    .status(ACTIVE.name())
                    .build();
    private static final List<Event> ENTITY_LIST = List.of(ENTITY);

    private static final List<EventDto> DTO_LIST = List.of(DTO);
    private final EventModelMapper mapper;

    EventModelMapperTest() {
        mapper = new EventModelMapper(new ManagerModelMapper(),new VenueModelMapper());
    }

    @Test
    void toEntityShouldCorrect() {
        Event actual = mapper.toEntity(DTO);
        Assertions.assertEquals(ENTITY,actual);
    }

    @Test
    void toDtoShouldCorrect() {
        EventDto actual = mapper.toDto(ENTITY);
        Assertions.assertEquals(DTO,actual);
    }

    @Test
    void toEntityListShouldCorrect() {
        List<Event> actual = mapper.toEntityList(DTO_LIST);
        Assertions.assertEquals(ENTITY_LIST,actual);
    }

    @Test
    void toDtoListShouldCorrect() {
        List<EventDto> actual = mapper.toDtoList(ENTITY_LIST);
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