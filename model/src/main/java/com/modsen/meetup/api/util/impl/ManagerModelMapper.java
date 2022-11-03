package com.modsen.meetup.api.util.impl;

import com.modsen.meetup.api.dto.ManagerDto;
import com.modsen.meetup.api.entity.Manager;
import com.modsen.meetup.api.exception.ModelException;
import com.modsen.meetup.api.util.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.modsen.meetup.api.exception.ModelExceptionCode.MODEL_MAPPER_EXCEPTION;

@Component
public class ManagerModelMapper implements ModelMapper<Manager, ManagerDto> {
    @Override
    public Manager toEntity(ManagerDto dto) throws ModelException {
        if(Objects.isNull(dto)) {
            throw new ModelException(MODEL_MAPPER_EXCEPTION.toString());
        }
        return Manager.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .lastname(dto.getLastname())
                .fullName(dto.getName() + " " + dto.getSurname() + " " + dto.getLastname())
                .build();
    }

    @Override
    public ManagerDto toDto(Manager entity) throws ModelException {
        if(Objects.isNull(entity)) {
            throw new ModelException(MODEL_MAPPER_EXCEPTION.toString());
        }
        return ManagerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .lastname(entity.getLastname())
                .build();
    }

    @Override
    public List<Manager> toEntityList(List<ManagerDto> dtoList) throws ModelException {
        if(Objects.isNull(dtoList)) {
            throw new ModelException(MODEL_MAPPER_EXCEPTION.toString());
        }
        List<Manager> list = new ArrayList<>();
        for (ManagerDto managerDto : dtoList) {
            Manager manager = toEntity(managerDto);
            list.add(manager);
        }
        return list;
    }

    @Override
    public List<ManagerDto> toDtoList(List<Manager> entityList) throws ModelException {
        if(Objects.isNull(entityList)) {
            throw new ModelException(MODEL_MAPPER_EXCEPTION.toString());
        }
        List<ManagerDto> list = new ArrayList<>();
        for (Manager manager : entityList) {
            ManagerDto managerDto = toDto(manager);
            list.add(managerDto);
        }
        return list;
    }
}
