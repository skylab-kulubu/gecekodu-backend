using System.ComponentModel.DataAnnotations;
using EntityLayer.Abstract;

namespace EntityLayer.Concrete
{
    public class Workshop:IEntity
    {
        [Key]
        public int WorkshopID { get; set; }

        [StringLength(50)]
        public string WorkshopName { get; set; }

        [StringLength(50)]
        public string WorkshopDescription { get; set;}
    }
}
