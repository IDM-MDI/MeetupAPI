package com.modsen.meetup.api.controller;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.dto.ResponsePage;
import com.modsen.meetup.api.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.modsen.meetup.api.util.PaginationUtil.paginationFilter;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    private final EventService service;

    @Autowired
    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponsePage<EventDto> getEvents(@RequestParam(required = false) PaginationInfo pagination) {
        return service.findByPage(paginationFilter(pagination));
    }

    @PostMapping
    public ResponsePage<EventDto> addEvent(@RequestBody EventDto event) {
        return service.save(event);
    }

    @PutMapping("/{id}")
    public ResponsePage<EventDto> updateEvent(@PathVariable long id,
                                              @RequestBody EventDto event) {
        return service.update(event,id);
    }

    @DeleteMapping("/{id}")
    public ResponsePage<EventDto> deleteEvent(@PathVariable long id) {
        return service.delete(id);
    }
}
