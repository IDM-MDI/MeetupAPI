package com.modsen.meetup.api.repository;

import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.entity.Event;
import com.modsen.meetup.api.exception.PersistenceException;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
    Optional<Event> findByID(long id);
    boolean isEventExistByID(long id);
    List<Event> findEventsByStatus(PaginationInfo pagination, String status);
    Event save(Event event) throws PersistenceException;
    Event update(Event event) throws PersistenceException;
    Event delete(long id) throws PersistenceException;
}
