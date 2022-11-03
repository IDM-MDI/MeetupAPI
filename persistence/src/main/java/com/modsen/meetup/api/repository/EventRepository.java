package com.modsen.meetup.api.repository;

import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.entity.Event;

import java.util.List;

public interface EventRepository {
    Event findByID(long id);
    boolean isEventExistByID(long id);
    List<Event> findEventsByStatus(PaginationInfo pagination, String status);
    Event save(Event event);
    Event update(Event event);
    Event delete(long id);
}
