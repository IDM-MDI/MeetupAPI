package com.modsen.meetup.api.service;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.dto.ResponsePage;
import com.modsen.meetup.api.exception.ServiceException;

public interface PageEventService {
    ResponsePage<EventDto> findByID(long id) throws ServiceException;
    ResponsePage<EventDto> findByActivePage(PaginationInfo paginationInfo) ;
    ResponsePage<EventDto> save(EventDto event) throws ServiceException;
    ResponsePage<EventDto> update(EventDto event, long id) throws ServiceException;
    ResponsePage<EventDto> delete(long id) throws ServiceException;
}
