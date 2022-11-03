package com.modsen.meetup.api.service.impl;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.dto.ResponsePage;
import com.modsen.meetup.api.repository.EventRepository;
import com.modsen.meetup.api.service.EventService;
import com.modsen.meetup.api.service.ManagerService;
import com.modsen.meetup.api.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseEventService implements EventService {
    private final EventRepository repository;
    private final ManagerService managerService;
    private final VenueService venueService;

    @Autowired
    public BaseEventService(EventRepository repository, ManagerService managerService, VenueService venueService) {
        this.repository = repository;
        this.managerService = managerService;
        this.venueService = venueService;
    }

    @Override
    public boolean isEventExistByID(long id) {
        return repository.isEventExistByID(id);
    }

    @Override
    public ResponsePage<EventDto> findByID(long id) {
        repository.findByID(id);
        return null;
    }

    @Override
    public ResponsePage<EventDto> findByPage(PaginationInfo paginationInfo) {
        return null;
    }

    @Override
    public ResponsePage<EventDto> save(EventDto event) {
        return null;
    }

    @Override
    public ResponsePage<EventDto> update(EventDto event, long id) {
        return null;
    }

    @Override
    public ResponsePage<EventDto> delete(long id) {
        return null;
    }
}
