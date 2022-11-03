package com.modsen.meetup.api.util.impl;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.entity.Event;
import com.modsen.meetup.api.exception.ModelException;
import com.modsen.meetup.api.exception.ModelExceptionCode;
import com.modsen.meetup.api.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.modsen.meetup.api.exception.ModelExceptionCode.MODEL_MAPPER_EXCEPTION;

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
    public Event toEntity(EventDto dto) throws ModelException {
        if(Objects.isNull(dto)) {
            throw new ModelException(MODEL_MAPPER_EXCEPTION.toString());
        }
        return Event.builder()
                .manager(managerMapper.toEntity(dto.getManager()))
                .topic(dto.getTopic())
                .description(dto.getDescription())
                .date(dto.getDate())
                .venue(venueMapper.toEntity(dto.getVenue()))
                .build();
    }

    @Override
    public EventDto toDto(Event entity) throws ModelException {
        if(Objects.isNull(entity)) {
            throw new ModelException(MODEL_MAPPER_EXCEPTION.toString());
        }
        return EventDto.builder()
                .manager(managerMapper.toDto(entity.getManager()))
                .topic(entity.getTopic())
                .description(entity.getDescription())
                .date(entity.getDate())
                .venue(venueMapper.toDto(entity.getVenue()))
                .build();
    }

    @Override
    public List<Event> toEntityList(List<EventDto> dtoList) throws ModelException {
        if(Objects.isNull(dtoList)) {
            throw new ModelException(MODEL_MAPPER_EXCEPTION.toString());
        }
        List<Event> list = new ArrayList<>();
        for (EventDto eventDto : dtoList) {
            Event event = toEntity(eventDto);
            list.add(event);
        }
        return list;
    }

    @Override
    public List<EventDto> toDtoList(List<Event> entityList) throws ModelException {
        if(Objects.isNull(entityList)) {
            throw new ModelException(MODEL_MAPPER_EXCEPTION.toString());
        }
        List<EventDto> list = new ArrayList<>();
        for (Event event : entityList) {
            EventDto eventDto = toDto(event);
            list.add(eventDto);
        }
        return list;
    }
}
