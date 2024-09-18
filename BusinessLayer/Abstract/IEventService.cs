using CoreLayer.Utilities.Results;
using EntityLayer.Concrete;

namespace BusinessLayer.Abstract;

public interface IEventService
{
    IDataResult<Event> GetById(int id);
    IDataResult<List<Event>> GetAll();
    IResult Add(Event @event);
    IResult Update(Event @event);
    IResult Delete(int id);
    IDataResult<Event> GetEventByName(string eventName);
}