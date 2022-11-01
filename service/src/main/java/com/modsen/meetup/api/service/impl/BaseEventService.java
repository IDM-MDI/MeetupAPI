package com.modsen.meetup.api.service.impl;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.service.EventService;
import com.modsen.meetup.api.service.ManagerService;
import com.modsen.meetup.api.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseEventService implements EventService {
    private ManagerService managerService;
    private VenueService venueService;

    @Autowired
    public BaseEventService(ManagerService managerService, VenueService venueService) {
        this.managerService = managerService;
        this.venueService = venueService;
    }

    @Override
    public boolean isEventExistByID(long id) {
        return false;
    }

    @Override
    public EventDto findByID(long id) {
        return null;
    }

    @Override
    public List<EventDto> findByPage(PaginationInfo paginationInfo) {
        return null;
    }

    @Override
    public EventDto save(EventDto event) {
        return null;
    }

    @Override
    public EventDto update(EventDto event) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
