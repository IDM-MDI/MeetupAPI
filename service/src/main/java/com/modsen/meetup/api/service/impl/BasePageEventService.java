package com.modsen.meetup.api.service.impl;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.dto.ResponsePage;
import com.modsen.meetup.api.exception.PersistenceException;
import com.modsen.meetup.api.exception.ServiceException;
import com.modsen.meetup.api.service.EventService;
import com.modsen.meetup.api.service.PageEventService;
import com.modsen.meetup.api.util.ResponseStatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.modsen.meetup.api.entity.EntityName.EVENT;
import static com.modsen.meetup.api.util.ResponseStatusUtil.pageFoundResponse;
import static com.modsen.meetup.api.util.ResponseStatusUtil.pageNotFoundResponse;

@Service
public class BasePageEventService implements PageEventService {

    private final EventService service;

    @Autowired
    public BasePageEventService(EventService service) {
        this.service = service;
    }

    @Override
    public ResponsePage<EventDto> findByID(long id) throws ServiceException {
        return ResponsePage.<EventDto>builder()
                .data(List.of(service.findByID(id)))
                .status(ResponseStatusUtil.byIdFoundResponse(EVENT.toString()))
                .build();
    }

    @Override
    public ResponsePage<EventDto> findByActivePage(PaginationInfo paginationInfo)  {
        List<EventDto> result = service.findByActivePage(paginationInfo);
        return ResponsePage.<EventDto>builder()
                .data(result)
                .paginationInfo(paginationInfo)
                .status(result.isEmpty() ? pageNotFoundResponse(EVENT.toString()) : pageFoundResponse(EVENT.toString()))
                .build();
    }

    @Override
    public ResponsePage<EventDto> save(EventDto event) throws ServiceException, PersistenceException {
        return ResponsePage.<EventDto>builder()
                .data(List.of(service.save(event)))
                .status(ResponseStatusUtil.saveResponse(EVENT.toString()))
                .build();
    }

    @Override
    public ResponsePage<EventDto> update(EventDto event, long id) throws ServiceException, PersistenceException {
        return ResponsePage.<EventDto>builder()
                .data(List.of(service.update(event,id)))
                .status(ResponseStatusUtil.updateResponse(EVENT.toString()))
                .build();
    }

    @Override
    public ResponsePage<EventDto> delete(long id) throws ServiceException, PersistenceException {
        return ResponsePage.<EventDto>builder()
                .data(List.of(service.delete(id)))
                .status(ResponseStatusUtil.deleteResponse(EVENT.toString()))
                .build();
    }
}
