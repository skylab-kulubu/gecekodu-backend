using System.ComponentModel.DataAnnotations;
using EntityLayer.Abstract;

namespace EntityLayer.Concrete;

public class UserOperationClaim : IEntity
{
    [Key]
    public int Id { get; set; }
    
    public int UserId { get; set; }
    
    public int OperationClaimId { get; set; }
}