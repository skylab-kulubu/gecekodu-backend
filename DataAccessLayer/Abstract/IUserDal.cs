using EntityLayer.Concrete;

namespace DataAccessLayer.Abstract
{
    public interface IUserDal : IGenericDal<User>
    {
        List<OperationClaim> GetClaims(User user);
    }
}