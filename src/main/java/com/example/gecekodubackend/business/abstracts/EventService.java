package com.example.gecekodubackend.business.abstracts;

import com.example.gecekodubackend.core.utilities.results.DataResult;
import com.example.gecekodubackend.core.utilities.results.Result;
import com.example.gecekodubackend.entity.concretes.Event;

import java.util.List;

public interface EventService {
    DataResult<List<Event>> getAllEvents();

    DataResult<Event> getEventById(int id);

    Result addEvent(Event event);

    Result updateEvent(int id, Event event);

    Result deleteEvent(int id);

   // Result addUserToEvent(int eventId, int userId);

    //DataResult<List<getEventsDto>> getAllEventsWithDto();
}