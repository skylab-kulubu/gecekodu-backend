using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EntityLayer.Concrete
{
    public class Workshop
    {
        [Key]
        public int WorkshopID { get; set; }

        [StringLength(50)]
        public string WorkshopName { get; set; }

        [StringLength(50)]
        public string WorkshopDescription { get; set;}
    }
}
