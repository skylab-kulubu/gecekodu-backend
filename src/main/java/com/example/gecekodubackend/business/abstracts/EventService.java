package com.example.gecekodubackend.business.abstracts;

import com.example.gecekodubackend.core.utilities.results.DataResult;
import com.example.gecekodubackend.core.utilities.results.Result;
import com.example.gecekodubackend.entity.concretes.Event;
import com.example.gecekodubackend.entity.dtos.event.CreateEventDto;
import com.example.gecekodubackend.entity.dtos.event.GetEventDto;

import java.util.List;

public interface EventService {
    DataResult<List<GetEventDto>> getAllEvents();

    DataResult<GetEventDto> getEventById(int id);

    Result addEvent(CreateEventDto createEventDto);

    Result updateEvent(int id, CreateEventDto createEventDto);

    Result updateEventByEntity(Event event);

    Result deleteEvent(int id);

    DataResult<Event> getEventEntityById(int id);

    DataResult<GetEventDto> getEventByName(String eventName);
}