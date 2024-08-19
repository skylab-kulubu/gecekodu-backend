using CoreLayer.Utilities.Results;
using EntityLayer.Concrete;

namespace BusinessLayer.Abstract;

public interface IEventService: IGenericService<Event>
{
    IDataResult<Event> GetEventByName(string eventName);
}