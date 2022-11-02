package com.modsen.meetup.api.repository;

import com.modsen.meetup.api.entity.Event;

public interface EventRepository {
    Event findByID(long id);
    boolean isEventExistByID(long id);
    Event save(Event event);
    Event update(Event event);
    Event delete(long id);
}
