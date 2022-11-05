package com.modsen.meetup.api.service.impl;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.dto.ResponsePage;
import com.modsen.meetup.api.entity.Event;
import com.modsen.meetup.api.service.EventService;
import com.modsen.meetup.api.util.ResponseStatusUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.modsen.meetup.api.entity.EntityName.EVENT;
import static com.modsen.meetup.api.entity.EntityStatus.ACTIVE;
import static com.modsen.meetup.api.util.ResponseStatusUtil.pageFoundResponse;
import static com.modsen.meetup.api.util.ResponseStatusUtil.pageNotFoundResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasePageEventServiceTest {
    private static final long ID = 1;
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

    private static final PaginationInfo PAGINATION =
            PaginationInfo.builder()
                    .page(0)
                    .direction(TEST)
                    .filter(TEST)
                    .size(1)
                    .build();
    @Mock
    private BaseEventService service;
    @InjectMocks
    private BasePageEventService pageEventService;
    @SneakyThrows
    @Test
    void findByIDShouldCorrect() {
        ResponsePage<EventDto> expected = ResponsePage.<EventDto>builder()
                .data(DTO_LIST)
                .status(ResponseStatusUtil.byIdFoundResponse(EVENT.toString()))
                .build();
        when(service.findByID(ID))
                .thenReturn(DTO);
        ResponsePage<EventDto> actual = pageEventService.findByID(ID);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void findByActivePageShouldCorrectResponse() {
        ResponsePage<EventDto> expected = ResponsePage.<EventDto>builder()
                .data(DTO_LIST)
                .paginationInfo(PAGINATION)
                .status(pageFoundResponse(EVENT.toString()))
                .build();
        when(service.findByActivePage(PAGINATION))
                .thenReturn(DTO_LIST);
        ResponsePage<EventDto> actual = pageEventService.findByActivePage(PAGINATION);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void findByActivePageShouldIncorrectResponse() {
        ResponsePage<EventDto> expected = ResponsePage.<EventDto>builder()
                .data(Collections.emptyList())
                .paginationInfo(PAGINATION)
                .status(pageNotFoundResponse(EVENT.toString()))
                .build();
        when(service.findByActivePage(PAGINATION))
                .thenReturn(Collections.emptyList());
        ResponsePage<EventDto> actual = pageEventService.findByActivePage(PAGINATION);
        Assertions.assertEquals(expected,actual);
    }

    @SneakyThrows
    @Test
    void saveShouldCorrect() {
        ResponsePage<EventDto> expected = ResponsePage.<EventDto>builder()
                .data(DTO_LIST)
                .status(ResponseStatusUtil.saveResponse(EVENT.toString()))
                .build();
        when(service.save(DTO))
                .thenReturn(DTO);
        ResponsePage<EventDto> actual = pageEventService.save(DTO);
        Assertions.assertEquals(expected,actual);
    }

    @SneakyThrows
    @Test
    void updateShouldCorrect() {
        ResponsePage<EventDto> expected = ResponsePage.<EventDto>builder()
                .data(DTO_LIST)
                .status(ResponseStatusUtil.updateResponse(EVENT.toString()))
                .build();
        when(service.update(DTO,ID))
                .thenReturn(DTO);
        ResponsePage<EventDto> actual = pageEventService.update(DTO, ID);
        Assertions.assertEquals(expected,actual);
    }

    @SneakyThrows
    @Test
    void deleteShouldCorrect() {
        ResponsePage<EventDto> expected = ResponsePage.<EventDto>builder()
                .data(DTO_LIST)
                .status(ResponseStatusUtil.deleteResponse(EVENT.toString()))
                .build();
        when(service.delete(ID))
                .thenReturn(DTO);
        ResponsePage<EventDto> actual = pageEventService.delete(ID);
        Assertions.assertEquals(expected,actual);
    }
}