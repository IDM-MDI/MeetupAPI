package com.modsen.meetup.api.service.impl;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.ManagerDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.dto.ResponsePage;
import com.modsen.meetup.api.entity.Event;
import com.modsen.meetup.api.exception.ModelException;
import com.modsen.meetup.api.exception.ServiceException;
import com.modsen.meetup.api.repository.EventRepository;
import com.modsen.meetup.api.service.EventService;
import com.modsen.meetup.api.service.ManagerService;
import com.modsen.meetup.api.service.VenueService;
import com.modsen.meetup.api.util.ResponseStatusUtil;
import com.modsen.meetup.api.util.impl.EventModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.modsen.meetup.api.entity.EntityName.EVENT;
import static com.modsen.meetup.api.entity.EntityStatus.ACTIVE;
import static com.modsen.meetup.api.validator.EventValidator.isEventDtoValid;

@Service
public class BaseEventService implements EventService {
    private final EventRepository repository;
    private final ManagerService managerService;
    private final VenueService venueService;

    private final EventModelMapper eventMapper;

    @Autowired
    public BaseEventService(EventRepository repository, ManagerService managerService, VenueService venueService, EventModelMapper eventMapper) {
        this.repository = repository;
        this.managerService = managerService;
        this.venueService = venueService;
        this.eventMapper = eventMapper;
    }

    @Override
    public boolean isEventExistByID(long id) {
        return repository.isEventExistByID(id);
    }

    @Override
    public ResponsePage<EventDto> findByID(long id) throws ModelException, ServiceException {
        if(!repository.isEventExistByID(id)) {
            throw new ServiceException(""); //TODO:FINISH EXCEPTION MESSAGE
        }
        return ResponsePage.<EventDto>builder()
                .data(List.of(eventMapper.toDto(repository.findByID(id))))
                .status(ResponseStatusUtil.byIdFoundResponse(EVENT.toString()))
                .build();
    }

    @Override
    public ResponsePage<EventDto> findByActivePage(PaginationInfo paginationInfo) throws ModelException {
        return ResponsePage.<EventDto>builder()
                .data(eventMapper.toDtoList(repository.findEventsByStatus(paginationInfo, ACTIVE.name())))
                .paginationInfo(paginationInfo)
                .status(ResponseStatusUtil.pageFoundResponse(EVENT.toString()))
                .build();
    }

    @Override
    public ResponsePage<EventDto> save(EventDto event) throws ModelException, ServiceException {
        if (!isEventDtoValid(event)) {
            throw new ServiceException(""); //TODO:FINISH EXCEPTION MESSAGE
        }
        event.setManager(managerService.save(event.getManager()));
        event.setVenue(venueService.save(event.getVenue()));

        return ResponsePage.<EventDto>builder()
                .data(List.of(eventMapper.toDto(repository.save(eventMapper.toEntity(event)))))
                .status(ResponseStatusUtil.updateResponse(EVENT.toString()))
                .build();
    }

    @Override
    public ResponsePage<EventDto> update(EventDto event, long id) throws ModelException {
        Event updatableEvent = eventMapper.toEntity(event);
        updatableEvent.setId(id);

        return ResponsePage.<EventDto>builder()
                .data(List.of(eventMapper.toDto(repository.update(updatableEvent))))
                .status(ResponseStatusUtil.updateResponse(EVENT.toString()))
                .build();
    }

    @Override
    public ResponsePage<EventDto> delete(long id) throws ModelException {
        return ResponsePage.<EventDto>builder()
                .data(List.of(eventMapper.toDto(repository.delete(id))))
                .status(ResponseStatusUtil.deleteResponse(EVENT.toString()))
                .build();
    }
}
