using System.ComponentModel.DataAnnotations;
using EntityLayer.Abstract;

namespace EntityLayer.Concrete;

public class OperationClaim: IEntity
{
    [Key]
    public int Id { get; set; }
    
    [StringLength(50)]
    public string Name { get; set; }
}