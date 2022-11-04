package com.modsen.meetup.api.util.impl;

import com.modsen.meetup.api.dto.VenueDto;
import com.modsen.meetup.api.entity.Venue;
import com.modsen.meetup.api.util.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class VenueModelMapper implements ModelMapper<Venue, VenueDto> {

    @Override
    public Venue toEntity(VenueDto dto)  {
        if(Objects.isNull(dto)) {
            return null;
        }
        return Venue.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @Override
    public VenueDto toDto(Venue entity)  {
        if(Objects.isNull(entity)) {
            return null;
        }
        return VenueDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public List<Venue> toEntityList(List<VenueDto> dtoList)  {
        if(Objects.isNull(dtoList)) {
            return Collections.emptyList();
        }
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<VenueDto> toDtoList(List<Venue> entityList)  {
        if(Objects.isNull(entityList)) {
            return Collections.emptyList();
        }
        return entityList.stream().map(this::toDto).toList();
    }
}
