using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
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
