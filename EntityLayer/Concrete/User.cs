using System.ComponentModel.DataAnnotations;
using EntityLayer.Abstract;

namespace EntityLayer.Concrete
{
    public class User:IEntity
    {
        [Key]
        public int Id { get; set; }

        [StringLength(50)]
        public string FirstName { get; set; }

        [StringLength(50)]
        public string LastName { get; set; }

        [StringLength(50)]
        public string Email { get; set; }
        
        [StringLength(50)]
        public string Password { get; set; }
        
        [StringLength(20)]
        public string Role { get; set; }
        
        public ICollection<UserWorkshop> UserWorkshops { get; set; }
        
        public ICollection<UserEvent> UserEvents { get; set; }
    }
}
