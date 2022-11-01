package com.modsen.meetup.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    @GetMapping()
    public ResponseEntity<String> getEvents() {
        return ResponseEntity.ok("get events");
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
