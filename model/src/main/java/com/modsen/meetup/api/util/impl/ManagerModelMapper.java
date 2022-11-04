package com.modsen.meetup.api.util.impl;

import com.modsen.meetup.api.dto.ManagerDto;
import com.modsen.meetup.api.entity.Manager;
import com.modsen.meetup.api.util.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.modsen.meetup.api.util.ManagerUtil.getFullName;

@Component
public class ManagerModelMapper implements ModelMapper<Manager, ManagerDto> {
    @Override
    public Manager toEntity(ManagerDto dto)  {
        if(Objects.isNull(dto)) {
            return null;
        }
        return Manager.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .lastname(dto.getLastname())
                .fullName(getFullName(dto))
                .build();
    }

    @Override
    public ManagerDto toDto(Manager entity)  {
        if(Objects.isNull(entity)) {
            return null;
        }
        return ManagerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .lastname(entity.getLastname())
                .build();
    }

    @Override
    public List<Manager> toEntityList(List<ManagerDto> dtoList)  {
        if(Objects.isNull(dtoList)) {
            return Collections.emptyList();
        }
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<ManagerDto> toDtoList(List<Manager> entityList)  {
        if(Objects.isNull(entityList)) {
            return Collections.emptyList();
        }
        return entityList.stream().map(this::toDto).toList();
    }
}
