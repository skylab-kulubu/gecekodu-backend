package com.example.gecekodubackend.business.concretes;

import com.example.gecekodubackend.business.abstracts.EventService;
import com.example.gecekodubackend.business.constants.EventMessages;
import com.example.gecekodubackend.core.utilities.results.*;
import com.example.gecekodubackend.dataAccess.abstracts.EventDao;
import com.example.gecekodubackend.entity.concretes.Event;
import com.example.gecekodubackend.entity.dtos.event.CreateEventDto;
import com.example.gecekodubackend.entity.dtos.event.GetEventDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventManager implements EventService {

    private final EventDao eventDao;

    @Autowired
    public EventManager(EventDao eventDao) {
        super();
        this.eventDao = eventDao;
    }

    @Override
    public DataResult<List<GetEventDto>> getAllEvents() {
        List<Event> events = eventDao.findAll();

        if(events.isEmpty()){
            return new ErrorDataResult<>(EventMessages.eventsNotFound);
        }

        List<GetEventDto> eventDtoList = new ArrayList<>();
        for(Event event: events){
            GetEventDto eventDto = new GetEventDto();
            BeanUtils.copyProperties(event, eventDto);
            eventDtoList.add(eventDto);
        }
        return new SuccessDataResult<>(eventDtoList, EventMessages.eventsBroughtSuccessfully);
    }

    @Override
    public DataResult<GetEventDto> getEventById(int id) {
        var result = eventDao.findById(id);

        if(result.isEmpty()){
            return new ErrorDataResult<>(EventMessages.eventNotFound);
        }

        GetEventDto eventDto = new GetEventDto();
        BeanUtils.copyProperties(result.get(), eventDto);
        return new SuccessDataResult<>(eventDto, EventMessages.eventBroughtSuccessfully);
    }

    @Override
    public Result addEvent(CreateEventDto createEventDto) {

        if(createEventDto.getEventName().isEmpty() || createEventDto.getDescription().isEmpty() || createEventDto.getDate() == null){
            return new ErrorResult(EventMessages.eventCouldNotBeAdded);
        }

        if(eventDao.existsByDate(createEventDto.getDate())){
            return new ErrorResult(EventMessages.eventAlreadyExists);
        }

        Event event = Event.builder()
                .eventName(createEventDto.getEventName())
                .date(createEventDto.getDate())
                .description(createEventDto.getDescription())
                .build();

        eventDao.save(event);

        return new SuccessResult(EventMessages.eventAddedSuccessfully);
    }

    @Override
    public Result updateEvent(int id, GetEventDto getEventDto) {
        var result = checkIfEventExists(id);

        if(getEventDto.getEventName().isEmpty() || getEventDto.getDescription().isEmpty() || getEventDto.getDate() == null){
            return new ErrorResult(EventMessages.eventCouldNotBeUpdated);
        }

        if(!result.isSuccess()){
            return new ErrorResult(EventMessages.eventNotFound);
        }

        var eventToUpdate = eventDao.findById(id).get();
        eventToUpdate.setEventName(getEventDto.getEventName());
        eventToUpdate.setDescription(getEventDto.getDescription());
        eventToUpdate.setDate(getEventDto.getDate());

        eventDao.save(eventToUpdate);
        return new SuccessResult(EventMessages.eventUpdatedSuccessfully);
    }

    @Override
    public Result deleteEvent(int id) {
        var result = eventDao.findById(id);

        if(result.isEmpty()){
            return new ErrorResult(EventMessages.eventNotFound);
        }

        this.eventDao.deleteById(id);
        return new SuccessResult(EventMessages.eventDeletedSuccessfully);
    }

    @Override
    public DataResult<Event> getEventEntityById(int id) {
        var result = eventDao.findById(id);

        if(result.isEmpty()){
            return new ErrorDataResult<>(EventMessages.eventNotFound);
        }

        return new SuccessDataResult<>(result.get(), EventMessages.eventsBroughtSuccessfully);
    }


    public Result checkIfEventExists(int id){
        Optional<Event> result = eventDao.findById(id);

        if(result.isEmpty()){
            return new ErrorResult(EventMessages.eventNotFound);
        }

        return new SuccessResult(EventMessages.eventAlreadyExists);
    }
}
