using CoreLayer.Utilities.Results;
using EntityLayer.Concrete;

namespace BusinessLayer.Abstract;

public interface IEventService
{
    IDataResult<List<Event>> GetAllEvents();

    IDataResult<Event> GetEventById(int eventId);

    IResult AddEvent(Event @event);

    IResult UpdateEvent(Event @event);

    IResult DeleteEvent(int eventId);

    IDataResult<Event> GetEventByName(string eventName);
}