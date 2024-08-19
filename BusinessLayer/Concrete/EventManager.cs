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
    
    public IDataResult<Event> GetEventByName(string eventName)
    {
        var @event = _eventDal.Get(@event => @event.Name == eventName);

        if (@event == null)
        {
            return new ErrorDataResult<Event>(@event, EventMessages.EventNotFound);
        }

        return new SuccessDataResult<Event>(@event, EventMessages.EventsBroughtSuccessfully);
    }

    private IResult CheckIfEventExists(int eventId)
    {
        var eventResult = _eventDal.Get(@event => @event.Id == eventId);
        if (eventResult == null)
        {
            return new ErrorResult(EventMessages.EventNotFound);
        }

        return new SuccessResult(EventMessages.EventsAlreadyExists);
    }

    private IResult CheckIfNameExists(string eventName)
    {
        var eventResult = _eventDal.Get(@event => @event.Name == eventName);
        if (eventResult == null)
        {
            return new ErrorResult(EventMessages.EventNotFound);
        }

        return new SuccessResult(EventMessages.EventsAlreadyExists);
    }

    public IDataResult<Event> GetById(int id)
    {
        var result = _eventDal.Get(@event => @event.Id == id);
        if (result == null)
        {
            return new ErrorDataResult<Event>(result, EventMessages.EventNotFound);
        }
        return new SuccessDataResult<Event>(result, EventMessages.EventsBroughtSuccessfully);
    }

    public IDataResult<List<Event>> GetAll()
    {
        var result = _eventDal.GetAll();
        if (result.Count == 0)
        {
            return new ErrorDataResult<List<Event>>(result, EventMessages.EventsNotFound);
        }
        return new SuccessDataResult<List<Event>>(result, EventMessages.EventsBroughtSuccessfully);
    }

    public IResult Add(Event p)
    {
        var result = CheckIfNameExists(p.Name);
        if (result.Success)
        {
            return new ErrorResult(EventMessages.EventAlreadyExists);
        }

        _eventDal.Insert(p);
        return new SuccessResult(EventMessages.EventAddedSuccessfully);
    }

    public IResult Update(Event p)
    {
        var result = GetById(p.Id);
        if (!result.Success)
        {
            return result;
        }

        _eventDal.Update(p);
        return new SuccessResult(EventMessages.EventUpdatedSuccessfully);
    }

    public IResult Delete(int id)
    {
        var result = GetById(id);
        if (!result.Success)
        {
            return result;
        }

        _eventDal.Delete(result.Data);
        return new SuccessResult(EventMessages.EventDeletedSuccessfully);
    }
}