package com.modsen.meetup.api.service.impl;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.ManagerDto;
import com.modsen.meetup.api.entity.Manager;
import com.modsen.meetup.api.exception.ServiceException;
import com.modsen.meetup.api.repository.ManagerRepository;
import com.modsen.meetup.api.repository.impl.BaseManagerRepository;
import com.modsen.meetup.api.util.impl.ManagerModelMapper;
import com.modsen.meetup.api.validator.ManagerValidator;
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
class BaseManagerServiceTest {
    private static final long ID = 1;
    private static final String TEST = "TEST";
    private static final String SPACE = " ";
    private static final Manager ENTITY =
            Manager.builder()
                    .id(ID)
                    .name(TEST)
                    .surname(TEST + TEST)
                    .lastname(TEST + TEST + TEST)
                    .fullName(TEST + SPACE + TEST + TEST + SPACE + TEST + TEST + TEST)
                    .build();
    private static final ManagerDto DTO =
            ManagerDto.builder()
                    .id(ID)
                    .name(TEST)
                    .surname(TEST + TEST)
                    .lastname(TEST + TEST + TEST)
                    .build();
    private static final List<Manager> ENTITY_LIST = List.of(ENTITY);

    private static final List<ManagerDto> DTO_LIST = List.of(DTO);

    @Mock
    private BaseManagerRepository repository;
    @Mock
    private ManagerModelMapper managerMapper;
    @InjectMocks
    private BaseManagerService service;

    @SneakyThrows
    @Test
    void findByIDShouldCorrect() {
        when(repository.findByID(ID))
                .thenReturn(Optional.of(ENTITY));
        when(managerMapper.toDto(ENTITY))
                .thenReturn(DTO);
        ManagerDto actual = service.findByID(ID);
        Assertions.assertEquals(DTO,actual);
    }

    @Test
    void findByIDShouldThrowServiceException() {
        when(repository.findByID(ID))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ServiceException.class,() -> service.findByID(ID));
    }
    @SneakyThrows
    @Test
    void findByFullNameShouldCorrect() {
        when(repository.findByFullName(ENTITY.getFullName()))
                .thenReturn(Optional.of(ENTITY));
        when(managerMapper.toDto(ENTITY))
                .thenReturn(DTO);
        ManagerDto actual = service.findByFullName(DTO);
        Assertions.assertEquals(DTO,actual);
    }

    @Test
    void findByFullNameShouldThrowServiceException() {
        when(repository.findByFullName(ENTITY.getFullName()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ServiceException.class,() -> service.findByFullName(DTO));
    }

    @Test
    void isManagerExistByIDShouldCorrect() {
        when(repository.isManagerExistByID(ID))
                .thenReturn(true);
        Assertions.assertTrue(service.isManagerExistByID(ID));
    }

    @Test
    void isManagerExistByFullNameShouldCorrect() {
        when(repository.isManagerExistByFullName(ENTITY.getFullName()))
                .thenReturn(true);
        Assertions.assertTrue(service.isManagerExistByFullName(DTO));
    }

    @Test
    void saveShouldCorrect() {
    }

    @Test
    void saveShouldThrowServiceException() {
        try (MockedStatic<ManagerValidator> validator = mockStatic(ManagerValidator.class)) {
            when(ManagerValidator.isManagerValid(DTO))
                    .thenReturn(false);
            Assertions.assertThrows(ServiceException.class, () -> service.save(DTO));
        }
    }
}