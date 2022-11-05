package com.modsen.meetup.api.service.impl;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.entity.Event;
import com.modsen.meetup.api.exception.ServiceException;
import com.modsen.meetup.api.repository.EventRepository;
import com.modsen.meetup.api.repository.impl.BaseEventRepository;
import com.modsen.meetup.api.service.ManagerService;
import com.modsen.meetup.api.service.VenueService;
import com.modsen.meetup.api.util.EventUtil;
import com.modsen.meetup.api.util.impl.EventModelMapper;
import com.modsen.meetup.api.validator.EventValidator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.modsen.meetup.api.entity.EntityStatus.ACTIVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BaseEventServiceTest {
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
    private BaseEventRepository repository;
    @Mock
    private BaseManagerService managerService;
    @Mock
    private BaseVenueService venueService;
    @Mock
    private EventModelMapper eventMapper;
    @InjectMocks
    private BaseEventService service;
    @Test
    void isEventExistByID() {
        when(repository.isEventExistByID(ID))
                .thenReturn(true);
        Assertions.assertTrue(service.isEventExistByID(ID));
    }

    @SneakyThrows
    @Test
    void findByIDShouldCorrect() {
        when(repository.findByID(ID))
                .thenReturn(Optional.of(ENTITY));
        when(eventMapper.toDto(ENTITY))
                .thenReturn(DTO);
        EventDto actual = service.findByID(ID);
        Assertions.assertEquals(DTO,actual);
    }

    @Test
    void findByIDShouldThrowServiceException() {
        when(repository.findByID(ID))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ServiceException.class,() -> service.findByID(ID));
    }
    @Test
    void findByActivePage() {
        when(repository.findEventsByStatus(PAGINATION,ACTIVE.name()))
                .thenReturn(ENTITY_LIST);
        when(eventMapper.toDtoList(ENTITY_LIST))
                .thenReturn(DTO_LIST);
        List<EventDto> actual = service.findByActivePage(PAGINATION);
        Assertions.assertEquals(DTO_LIST,actual);
    }

    @SneakyThrows
    @Test
    void saveShouldCorrect() {
        try (MockedStatic<EventValidator> validator = mockStatic(EventValidator.class)) {
            when(EventValidator.isEventDtoValid(DTO))
                    .thenReturn(true);
            when(managerService.save(DTO.getManager()))
                    .thenReturn(DTO.getManager());
            when(venueService.save(DTO.getVenue()))
                    .thenReturn(DTO.getVenue());
            when(eventMapper.toEntity(DTO))
                    .thenReturn(ENTITY);
            when(repository.save(ENTITY))
                    .thenReturn(ENTITY);
            when(eventMapper.toDto(ENTITY))
                    .thenReturn(DTO);
            EventDto actual = service.save(DTO);
            Assertions.assertEquals(DTO,actual);
        }
    }
    @Test
    void saveShouldThrowServiceException() {
        try (MockedStatic<EventValidator> validator = mockStatic(EventValidator.class)) {
            when(EventValidator.isEventDtoValid(DTO))
                    .thenReturn(false);
            Assertions.assertThrows(ServiceException.class, () -> service.save(DTO));
        }
    }

    @SneakyThrows
    @Test
    void updateShouldCorrect() {
        try (MockedStatic<EventUtil> eventUtil = mockStatic(EventUtil.class)) {
            when(repository.isEventExistByID(ID))
                    .thenReturn(true);
            when(repository.findByID(ID))
                    .thenReturn(Optional.of(ENTITY));
            when(eventMapper.toDto(ENTITY))
                    .thenReturn(DTO);
            when(eventMapper.toEntity(DTO))
                    .thenReturn(ENTITY);
            when(EventUtil.uniteUpdatableEvent(ENTITY,ENTITY))
                    .thenReturn(ENTITY);
            when(repository.update(ENTITY))
                    .thenReturn(ENTITY);
            EventDto actual = service.update(DTO, ID);
            Assertions.assertEquals(DTO,actual);
        }
    }
    @Test
    void updateShouldThrowServiceException() {
        when(repository.isEventExistByID(ID))
                .thenReturn(false);
        Assertions.assertThrows(ServiceException.class, () -> service.update(DTO,ID));
    }

    @SneakyThrows
    @Test
    void delete() {
        when(repository.delete(ID))
                .thenReturn(ENTITY);
        when(eventMapper.toDto(ENTITY))
                .thenReturn(DTO);
        EventDto actual = service.delete(ID);
        Assertions.assertEquals(DTO,actual);
    }
}