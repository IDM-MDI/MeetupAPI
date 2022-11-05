package com.modsen.meetup.api.util.impl;

import com.modsen.meetup.api.dto.ManagerDto;
import com.modsen.meetup.api.entity.Manager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ManagerModelMapperTest {
    private static final String TEST = "TEST";
    private static final String SPACE = " ";
    private static final Manager ENTITY =
            Manager.builder()
                    .id(1)
                    .name(TEST)
                    .surname(TEST + TEST)
                    .lastname(TEST + TEST + TEST)
                    .fullName(TEST + SPACE + TEST + TEST + SPACE + TEST + TEST + TEST)
                    .build();
    private static final ManagerDto DTO =
            ManagerDto.builder()
                    .id(1)
                    .name(TEST)
                    .surname(TEST + TEST)
                    .lastname(TEST + TEST + TEST)
                    .build();
    private static final List<Manager> ENTITY_LIST = List.of(ENTITY);

    private static final List<ManagerDto> DTO_LIST = List.of(DTO);
    private final ManagerModelMapper mapper;

    ManagerModelMapperTest() {
        this.mapper = new ManagerModelMapper();
    }

    @Test
    void toEntityShouldCorrect() {
        Manager actual = mapper.toEntity(DTO);
        Assertions.assertEquals(ENTITY,actual);
    }

    @Test
    void toDtoShouldCorrect() {
        ManagerDto actual = mapper.toDto(ENTITY);
        Assertions.assertEquals(DTO,actual);
    }

    @Test
    void toEntityListShouldCorrect() {
        List<Manager> actual = mapper.toEntityList(DTO_LIST);
        Assertions.assertEquals(ENTITY_LIST,actual);
    }

    @Test
    void toDtoListShouldCorrect() {
        List<ManagerDto> actual = mapper.toDtoList(ENTITY_LIST);
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