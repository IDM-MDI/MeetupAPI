package com.modsen.meetup.api.service.impl;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.entity.Event;
import com.modsen.meetup.api.exception.PersistenceException;
import com.modsen.meetup.api.exception.ServiceException;
import com.modsen.meetup.api.repository.EventRepository;
import com.modsen.meetup.api.service.EventService;
import com.modsen.meetup.api.service.ManagerService;
import com.modsen.meetup.api.service.VenueService;
import com.modsen.meetup.api.util.impl.EventModelMapper;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.modsen.meetup.api.entity.EntityStatus.ACTIVE;
import static com.modsen.meetup.api.exception.ServiceExceptionCode.ENTITY_NOT_FOUND;
import static com.modsen.meetup.api.exception.ServiceExceptionCode.ENTITY_NOT_VALID;
import static com.modsen.meetup.api.util.EventUtil.uniteUpdatableEvent;
import static com.modsen.meetup.api.validator.EventValidator.isEventDtoValid;

@Service
@Slf4j
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
    public EventDto findByID(long id) throws ServiceException {
        return eventMapper.toDto(
                repository.findByID(id)
                        .orElseThrow(() -> {
                            log.error(ENTITY_NOT_FOUND.toString());
                            return new ServiceException(ENTITY_NOT_FOUND.toString());
                        }));

    }

    @Override
    public List<EventDto> findByActivePage(PaginationInfo paginationInfo) {
        return eventMapper.toDtoList(repository.findEventsByStatus(paginationInfo, ACTIVE.name()));
    }

    @Override
    public EventDto save(EventDto event) throws ServiceException, PersistenceException {
        if (!isEventDtoValid(event)) {
            log.error(ENTITY_NOT_VALID.toString());
            throw new ServiceException(ENTITY_NOT_VALID.toString());
        }
        event.setManager(managerService.save(event.getManager()));
        event.setVenue(venueService.save(event.getVenue()));
        event.setStatus(ACTIVE.name());
        return eventMapper.toDto(repository.save(eventMapper.toEntity(event)));
    }

    @Override
    public EventDto update(EventDto event, long id) throws ServiceException, PersistenceException {
        if(!isEventExistByID(id)) {
            log.error(ENTITY_NOT_VALID.toString());
            throw new ServiceException(ENTITY_NOT_FOUND.toString());
        }
        EventDto fromDB = findByID(id);
        updateHandler(event,fromDB);
        Event updatableEvent = eventMapper.toEntity(event);
        Event readyToUpdate = uniteUpdatableEvent(updatableEvent, eventMapper.toEntity(fromDB));
        Event result = repository.update(readyToUpdate);
        log.info("Event {} was updated", result);
        return eventMapper.toDto(result);
    }

    @Override
    public EventDto delete(long id) throws PersistenceException {
        Event result = repository.delete(id);
        log.info("Event {} was successfully deleted", result);
        return eventMapper.toDto(result);
    }

    private void updateHandler(EventDto updatable, EventDto fromDB) throws ServiceException, PersistenceException {
        if(Objects.nonNull(updatable.getManager()) &&
                !managerService.isManagerExistByFullName(updatable.getManager())) {
            updatable.setManager(managerService.save(updatable.getManager()));
        } else {
            updatable.setManager(fromDB.getManager());
        }
        if(Objects.nonNull(updatable.getVenue()) &&
                !venueService.isVenueExistByName(updatable.getVenue().getName())) {
            updatable.setVenue(venueService.save(updatable.getVenue()));
        } else {
            updatable.setVenue(fromDB.getVenue());
        }
    }
}
