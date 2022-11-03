package com.modsen.meetup.api.service;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.dto.ResponsePage;
import com.modsen.meetup.api.exception.ModelException;
import com.modsen.meetup.api.exception.ServiceException;

public interface EventService {
    boolean isEventExistByID(long id);
    ResponsePage<EventDto> findByID(long id) throws ModelException, ServiceException;
    ResponsePage<EventDto> findByActivePage(PaginationInfo paginationInfo) throws ModelException;
    ResponsePage<EventDto> save(EventDto event) throws ModelException, ServiceException;
    ResponsePage<EventDto> update(EventDto event, long id) throws ModelException, ServiceException;
    ResponsePage<EventDto> delete(long id) throws ModelException, ServiceException;
}
