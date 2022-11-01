package com.modsen.meetup.api.service;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;

import java.util.List;

public interface EventService {
    boolean isEventExistByID(long id);
    EventDto findByID(long id);
    List<EventDto> findByPage(PaginationInfo paginationInfo);
    EventDto save(EventDto event);
    EventDto update(EventDto event);
    void delete(long id);
}
