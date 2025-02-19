package com.example.gecekodubackend.webApi.controllers;

import com.example.gecekodubackend.business.abstracts.EventService;
import com.example.gecekodubackend.entity.dtos.event.CreateEventDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webApi/events")
public class EventsController {

    private final EventService eventService;

    @Autowired
    public EventsController(EventService eventService) {
        super();
        this.eventService = eventService;
    }

    @GetMapping("/getAllEvents")
    public ResponseEntity<?> getAllEvents() {
        var result = eventService.getAllEvents();

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getEventById/{id}")
    public ResponseEntity<?> getEventById(@PathVariable int id) {
        var result = eventService.getEventById(id);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/addEvent")
    public ResponseEntity<?> addEvent(@Valid @RequestBody CreateEventDto createEventDto) {
        var result = eventService.addEvent(createEventDto);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateEvent/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable("id") int id, @Valid @RequestBody CreateEventDto createEventDto) {
        var result = eventService.updateEvent(id, createEventDto);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteEvent/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable int id) {
        var result = eventService.deleteEvent(id);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getEventByName/{eventName}")
    public ResponseEntity<?> getEventByName(@PathVariable String eventName) {
        var result = eventService.getEventByName(eventName);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
