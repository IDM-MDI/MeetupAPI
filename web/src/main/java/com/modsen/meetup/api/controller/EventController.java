package com.modsen.meetup.api.controller;

import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.dto.ResponsePage;
import com.modsen.meetup.api.service.EventService;
import com.modsen.meetup.api.util.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.modsen.meetup.api.util.PaginationUtil.paginationFilter;

@RestController
@Slf4j
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
    public ResponseEntity<String> addEvent() {
        return ResponseEntity.ok("add new event");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable long id) {
        return ResponseEntity.ok("update exist event");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable long id) {
        return ResponseEntity.ok("delete exist event");
    }
}
