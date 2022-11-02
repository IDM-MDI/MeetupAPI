package com.modsen.meetup.api.service;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.dto.ResponsePage;

public interface EventService {
    boolean isEventExistByID(long id);
    ResponsePage<EventDto> findByID(long id);
    ResponsePage<EventDto> findByPage(PaginationInfo paginationInfo);
    ResponsePage<EventDto> save(EventDto event);
    ResponsePage<EventDto> update(EventDto event);
    ResponsePage<EventDto> delete(long id);
}
