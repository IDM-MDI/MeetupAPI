package com.modsen.meetup.api.repository;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.entity.Event;

public interface EventRepository {
    Event findByID(long id);
}
