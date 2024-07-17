using BusinessLayer.Abstract;
using BusinessLayer.Constants;
using CoreLayer.Utilities.Results;
using DataAccessLayer.Abstract;
using EntityLayer.Concrete;

namespace BusinessLayer.Concrete;

public class EventManager : IEventService
{
    private IEventDal _eventDal;

    public EventManager(IEventDal eventDal)
    {
        _eventDal = eventDal;
    }

    public IDataResult<List<Event>> GetAllEvents()
    {
        return new SuccessDataResult<List<Event>>(_eventDal.GetAll(), EventMessages.EventsBroughtSuccessfully);
    }

    public IDataResult<Event> GetEventById(int eventId)
    {
        var @event = _eventDal.Get(@event => @event.EventID == eventId);
        if (@event == null)
        {
            return new ErrorDataResult<Event>(@event, EventMessages.EventNotFound);
        }

        return new SuccessDataResult<Event>(@event, EventMessages.EventsBroughtSuccessfully);
    }

    public IResult AddEvent(Event @event)
    {
        var eventResult = CheckIfEventExists(@event.EventID);
        if (eventResult.Success)
        {
            return new ErrorResult(eventResult.Message);
        }

        _eventDal.Insert(@event);
        return new SuccessResult(EventMessages.EventAddedSuccessfully);
    }

    public IResult UpdateEvent(Event @event)
    {
        var eventResult = GetEventById(@event.EventID);
        if (!eventResult.Success)
        {
            return eventResult;
        }

        _eventDal.Update(@event);
        return new SuccessResult(EventMessages.EventUpdatedSuccessfully);
    }

    public IResult DeleteEvent(int eventID)
    {
        var eventResult = GetEventById(eventID);
        if (!eventResult.Success)
        {
            return eventResult;
        }

        _eventDal.Delete(eventResult.Data);
        return new SuccessResult(EventMessages.EventDeletedSuccessfully);
    }

    public IDataResult<Event> GetEventByName(string eventName)
    {
        var @event = _eventDal.Get(@event => @event.EventName == eventName);

        if (@event == null)
        {
            return new ErrorDataResult<Event>(@event, EventMessages.EventNotFound);
        }

        return new SuccessDataResult<Event>(@event, EventMessages.EventsBroughtSuccessfully);
    }

    private IResult CheckIfEventExists(int eventId)
    {
        var eventResult = _eventDal.Get(@event => @event.EventID == eventId);
        if (eventResult == null)
        {
            return new ErrorResult(EventMessages.EventNotFound);
        }

        return new SuccessResult(EventMessages.EventsAlreadyExists);
    }

    private IResult CheckIfNameExists(string eventName)
    {
        var eventResult = _eventDal.Get(@event => @event.EventName == eventName);
        if (eventResult == null)
        {
            return new ErrorResult(EventMessages.EventNotFound);
        }

        return new SuccessResult(EventMessages.EventsAlreadyExists);
    }
}