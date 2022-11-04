package com.modsen.meetup.api.service;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.exception.ServiceException;

import java.util.List;

public interface EventService {
    boolean isEventExistByID(long id);
    EventDto findByID(long id) throws ServiceException;
    List<EventDto> findByActivePage(PaginationInfo paginationInfo) ;
    EventDto save(EventDto event) throws ServiceException;
    EventDto update(EventDto event, long id) throws ServiceException;
    EventDto delete(long id) throws ServiceException;
}
