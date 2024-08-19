using System.ComponentModel.DataAnnotations;

namespace EntityLayer.Concrete;

public class UserEvent
{
    [Key]
    public int Id { get; set; }
    public int UserID { get; set; }
    public User User { get; set; }
    
    public int EventID { get; set; }
    public Event Event { get; set; }
}