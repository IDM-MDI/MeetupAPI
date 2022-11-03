package com.modsen.meetup.api.util.impl;

import com.modsen.meetup.api.dto.VenueDto;
import com.modsen.meetup.api.entity.Venue;
import com.modsen.meetup.api.exception.ModelException;
import com.modsen.meetup.api.util.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.modsen.meetup.api.exception.ModelExceptionCode.MODEL_MAPPER_EXCEPTION;

@Component
public class VenueModelMapper implements ModelMapper<Venue, VenueDto> {

    @Override
    public Venue toEntity(VenueDto dto) throws ModelException {
        if(Objects.isNull(dto)) {
            throw new ModelException(MODEL_MAPPER_EXCEPTION.toString());
        }
        return Venue.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @Override
    public VenueDto toDto(Venue entity) throws ModelException {
        if(Objects.isNull(entity)) {
            throw new ModelException(MODEL_MAPPER_EXCEPTION.toString());
        }
        return VenueDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public List<Venue> toEntityList(List<VenueDto> dtoList) throws ModelException {
        if(Objects.isNull(dtoList)) {
            throw new ModelException(MODEL_MAPPER_EXCEPTION.toString());
        }
        List<Venue> list = new ArrayList<>();
        for (VenueDto venueDto : dtoList) {
            Venue venue = toEntity(venueDto);
            list.add(venue);
        }
        return list;
    }

    @Override
    public List<VenueDto> toDtoList(List<Venue> entityList) throws ModelException {
        if(Objects.isNull(entityList)) {
            throw new ModelException(MODEL_MAPPER_EXCEPTION.toString());
        }
        List<VenueDto> list = new ArrayList<>();
        for (Venue venue : entityList) {
            VenueDto venueDto = toDto(venue);
            list.add(venueDto);
        }
        return list;
    }
}
