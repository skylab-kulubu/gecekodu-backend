package com.example.gecekodubackend.webApi.controllers;

import com.example.gecekodubackend.business.abstracts.EventService;
import com.example.gecekodubackend.entity.concretes.Event;
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
    public ResponseEntity<?> getAllEvents(){
        var result = eventService.getAllEvents();

        if(result.isSuccess()){
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getEventById/{id}")
    public ResponseEntity<?> getEventById(@PathVariable int id){
        var result = eventService.getEventById(id);

        if(result.isSuccess()){
            return  ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/addEvent")
    public ResponseEntity<?> addEvent(@Valid @RequestBody Event event){
        var result = eventService.addEvent(event);

        if(result.isSuccess()){
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateEvent/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable("id") int id, @Valid @RequestBody Event event){
        var result = eventService.updateEvent(id, event);

        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }

        return  ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteEvent/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable int id){
        var result = eventService.deleteEvent(id);

        if (result.isSuccess()){
            return ResponseEntity.ok().body(result);
        }

        return  ResponseEntity.badRequest().body(result);
    }


}
