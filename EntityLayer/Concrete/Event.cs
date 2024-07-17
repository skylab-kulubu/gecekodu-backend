using System.ComponentModel.DataAnnotations;
using EntityLayer.Abstract;

namespace EntityLayer.Concrete
{
    public class Event:IEntity
    {
        [Key]
        public int EventID { get; set; }

        [StringLength(50)]
        public string EventName { get; set; }

        [StringLength(50)]
        public string EventDescription { get; set; }

        public DateTime EventDate { get; set; }
    }
}
