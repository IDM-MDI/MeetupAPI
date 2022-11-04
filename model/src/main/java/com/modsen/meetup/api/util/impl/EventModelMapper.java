package com.modsen.meetup.api.util.impl;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.entity.Event;
import com.modsen.meetup.api.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class EventModelMapper implements ModelMapper<Event, EventDto> {
    private final ManagerModelMapper managerMapper;
    private final VenueModelMapper venueMapper;

    @Autowired
    public EventModelMapper(ManagerModelMapper managerMapper, VenueModelMapper venueMapper) {
        this.managerMapper = managerMapper;
        this.venueMapper = venueMapper;
    }

    @Override
    public Event toEntity(EventDto dto)  {
        if(Objects.isNull(dto)) {
            return null;
        }
        return Event.builder()
                .id(dto.getId())
                .manager(managerMapper.toEntity(dto.getManager()))
                .topic(dto.getTopic())
                .description(dto.getDescription())
                .date(dto.getDate())
                .venue(venueMapper.toEntity(dto.getVenue()))
                .status(dto.getStatus())
                .build();
    }

    @Override
    public EventDto toDto(Event entity)  {
        if(Objects.isNull(entity)) {
            return null;
        }
        return EventDto.builder()
                .id(entity.getId())
                .manager(managerMapper.toDto(entity.getManager()))
                .topic(entity.getTopic())
                .description(entity.getDescription())
                .date(entity.getDate())
                .venue(venueMapper.toDto(entity.getVenue()))
                .status(entity.getStatus())
                .build();
    }

    @Override
    public List<Event> toEntityList(List<EventDto> dtoList)  {
        if(Objects.isNull(dtoList)) {
            return Collections.emptyList();
        }
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<EventDto> toDtoList(List<Event> entityList)  {
        if(Objects.isNull(entityList)) {
            return Collections.emptyList();
        }
        List<EventDto> list = new ArrayList<>();
        for (Event event : entityList) {
            EventDto eventDto = toDto(event);
            list.add(eventDto);
        }
        return entityList.stream().map(this::toDto).toList();
    }
}
