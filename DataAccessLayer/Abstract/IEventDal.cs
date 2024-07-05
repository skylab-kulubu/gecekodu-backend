using EntityLayer.Concrete;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccessLayer.Abstract
{
    public interface IEventDal
    {
        List<Event> ListEvent();
        void AddEvent(Event @event);
        void DeleteEvent(Event @event);
        void UpdateEvent(Event @event);
        Event GetEvent(int id);
    }
}