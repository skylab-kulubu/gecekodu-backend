using System.ComponentModel.DataAnnotations;

namespace EntityLayer.Concrete;

public class UserWorkshop
{
    [Key]
    public int Id { get; set; }
    public int UserID { get; set; }
    public User User { get; set; }

    public int WorkshopID { get; set; }
    public Workshop Workshop { get; set; }
}