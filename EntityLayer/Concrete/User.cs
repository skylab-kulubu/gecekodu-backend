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

        [StringLength(100)]
        public string Email { get; set; }
    
        [StringLength(500)]
        public byte[] PasswordSalt { get; set; }
        
        [StringLength(500)]
        public byte[] PasswordHash { get; set; }
        
        public ICollection<UserWorkshop> UserWorkshops { get; set; }
        
        public ICollection<UserEvent> UserEvents { get; set; }
    }
}
