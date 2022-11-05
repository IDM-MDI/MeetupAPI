package com.modsen.meetup.api.service.impl;

import com.modsen.meetup.api.dto.ManagerDto;
import com.modsen.meetup.api.dto.VenueDto;
import com.modsen.meetup.api.entity.Venue;
import com.modsen.meetup.api.exception.ServiceException;
import com.modsen.meetup.api.repository.VenueRepository;
import com.modsen.meetup.api.util.impl.VenueModelMapper;
import com.modsen.meetup.api.validator.ManagerValidator;
import com.modsen.meetup.api.validator.VenueValidator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BaseVenueServiceTest {
    private static final long ID = 1;
    private static final String TEST = "TEST";
    private static final Venue ENTITY =
            Venue.builder()
                    .id(ID)
                    .name(TEST)
                    .build();
    private static final VenueDto DTO =
            VenueDto.builder()
                    .id(ID)
                    .name(TEST)
                    .build();
    private static final List<Venue> ENTITY_LIST = List.of(ENTITY);

    private static final List<VenueDto> DTO_LIST = List.of(DTO);

    @Mock
    private VenueRepository repository;
    @Mock
    private VenueModelMapper venueMapper;
    @InjectMocks
    private BaseVenueService service;
    @SneakyThrows
    @Test
    void findByIDShouldCorrect() {
        when(repository.findByID(ID))
                .thenReturn(Optional.of(ENTITY));
        when(venueMapper.toDto(ENTITY))
                .thenReturn(DTO);
        VenueDto actual = service.findByID(ID);
        Assertions.assertEquals(DTO,actual);
    }

    @Test
    void findByIDShouldThrowServiceException() {
        when(repository.findByID(ID))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ServiceException.class,() -> service.findByID(ID));
    }

    @Test
    void isVenueExistByIDShouldCorrect() {
        when(repository.isVenueExistByID(ID))
                .thenReturn(true);
        Assertions.assertTrue(service.isVenueExistByID(ID));
    }

    @Test
    void isVenueExistByNameShouldCorrect() {
        when(repository.isVenueExistByName(DTO.getName()))
                .thenReturn(true);
        Assertions.assertTrue(service.isVenueExistByName(DTO.getName()));
    }

    @SneakyThrows
    @Test
    void findByNameShouldCorrect() {
        when(repository.findByName(DTO.getName()))
                .thenReturn(Optional.of(ENTITY));
        when(venueMapper.toDto(ENTITY))
                .thenReturn(DTO);
        VenueDto actual = service.findByName(DTO.getName());
        Assertions.assertEquals(DTO,actual);
    }
    @SneakyThrows
    @Test
    void findByNameShouldThrowServiceException() {
        when(repository.findByName(DTO.getName()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ServiceException.class,() -> service.findByName(DTO.getName()));
    }
    @SneakyThrows
    @Test
    void saveShouldCorrectExist() {
        try (MockedStatic<VenueValidator> validator = mockStatic(VenueValidator.class)) {
            when(VenueValidator.isVenueValid(DTO))
                    .thenReturn(true);
            when(repository.isVenueExistByName(ENTITY.getName()))
                    .thenReturn(true);
            when(repository.findByName(ENTITY.getName()))
                    .thenReturn(Optional.of(ENTITY));
            when(venueMapper.toDto(ENTITY))
                    .thenReturn(DTO);
            VenueDto actual = service.save(DTO);
            Assertions.assertEquals(DTO,actual);
        }
    }
    @SneakyThrows
    @Test
    void saveShouldCorrectNotExist() {
        try (MockedStatic<VenueValidator> validator = mockStatic(VenueValidator.class)) {
            when(VenueValidator.isVenueValid(DTO))
                    .thenReturn(true);
            when(repository.isVenueExistByName(ENTITY.getName()))
                    .thenReturn(false);
            when(venueMapper.toEntity(DTO))
                    .thenReturn(ENTITY);
            when(repository.save(ENTITY))
                    .thenReturn(ENTITY);
            when(venueMapper.toDto(ENTITY))
                    .thenReturn(DTO);
            VenueDto actual = service.save(DTO);
            Assertions.assertEquals(DTO,actual);
        }
    }
    @Test
    void saveShouldThrowServiceException() {
        try (MockedStatic<VenueValidator> validator = mockStatic(VenueValidator.class)) {
            when(VenueValidator.isVenueValid(DTO))
                    .thenReturn(false);
            Assertions.assertThrows(ServiceException.class, () -> service.save(DTO));
        }
    }
}