package com.modsen.meetup.api.controller;

import com.modsen.meetup.api.config.SwaggerConfig;
import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.dto.ResponsePage;
import com.modsen.meetup.api.exception.PersistenceException;
import com.modsen.meetup.api.exception.ServiceException;
import com.modsen.meetup.api.service.PageEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
@Api(tags = {SwaggerConfig.EVENT_TAG})
public class EventController {
    private final PageEventService service;

    @Autowired
    public EventController(PageEventService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation("Get event list by using pagination")
    public ResponsePage<EventDto> getActiveEvents(
            @RequestParam(defaultValue = "5") long size,
            @RequestParam(defaultValue = "0") long page,
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction
    )  {
        return service.findByActivePage(
                PaginationInfo.builder()
                        .size(size)
                        .page(page)
                        .filter(filter)
                        .sort(sort)
                        .direction(direction.toUpperCase())
                        .build()
        );
    }

    @PostMapping
    @ApiOperation("Create event by data from object")
    public ResponsePage<EventDto> addEvent(@RequestBody EventDto event) throws ServiceException, PersistenceException {
        return service.save(event);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get single event by ID from path")
    public ResponsePage<EventDto> getEventByID(@PathVariable long id) throws ServiceException {
        return service.findByID(id);
    }
    @PatchMapping("/{id}")
    @ApiOperation("Update database event by ID to data from the body")
    public ResponsePage<EventDto> updateEvent(@PathVariable long id,
                                              @RequestBody EventDto event) throws ServiceException, PersistenceException {
        return service.update(event,id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete event by ID from path")
    public ResponsePage<EventDto> deleteEvent(@PathVariable long id) throws ServiceException, PersistenceException {
        return service.delete(id);
    }
}
