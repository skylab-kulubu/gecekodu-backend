package com.example.gecekodubackend.business.concretes;

import com.example.gecekodubackend.business.abstracts.EventService;
import com.example.gecekodubackend.business.abstracts.UserService;
import com.example.gecekodubackend.business.constants.EventMessages;
import com.example.gecekodubackend.business.constants.UserMessages;
import com.example.gecekodubackend.core.utilities.results.*;
import com.example.gecekodubackend.dataAccess.abstracts.EventDao;
import com.example.gecekodubackend.entity.concretes.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /*
    @Override
    public DataResult<List<getEventsDto>> getAllEventsWithDto() {
        return new SuccessDataResult<>(eventDao.getAllEventsWithDto(), EventMessages.eventsBroughtSuccessfully);
    }
     */

    @Override
    public DataResult<List<Event>> getAllEvents() {
        List<Event> events = eventDao.findAll();

        if(events.isEmpty()){
            return new ErrorDataResult<>(EventMessages.eventsNotFound);
        }

        return new SuccessDataResult<>(events, EventMessages.eventsBroughtSuccessfully);
    }

    @Override
    public DataResult<Event> getEventById(int id) {
        var result = eventDao.findById(id);

        if(result.isEmpty()){
            return new ErrorDataResult<>(EventMessages.eventNotFound);
        }

        return new SuccessDataResult<>(result.get(),EventMessages.eventBroughtSuccessfully);
    }

    @Override
    public Result addEvent(Event event) {
        var result = checkIfEventExists(event.getEventId());

        if(result.isSuccess()){
            return new ErrorResult(EventMessages.eventAlreadyExists);
        }

        eventDao.save(event);
        return new SuccessResult(EventMessages.eventAddedSuccessfully);
    }

    @Override
    public Result updateEvent(int id, Event event) {
        var result = checkIfEventExists(event.getEventId());

        if(result == null){
            return new ErrorResult(EventMessages.eventNotFound);
        }

        DataResult<Event> eventToUpdate = getEventById(id);
        eventToUpdate.getData().setEventName(event.getEventName());
        eventToUpdate.getData().setDescription(event.getDescription());
        eventToUpdate.getData().setDate(event.getDate());
        eventDao.save(eventToUpdate.getData());

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



    public Result checkIfEventExists(int id){
        Optional<Event> result = eventDao.findById(id);

        if(result.isEmpty()){
            return new ErrorResult(EventMessages.eventNotFound);
        }

        return new SuccessResult(EventMessages.eventAlreadyExists);
    }
}
