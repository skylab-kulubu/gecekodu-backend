using DataAccessLayer.Abstract;
using DataAccessLayer.Concrete.Repositories;
using EntityLayer.Concrete;

namespace DataAccessLayer.Concrete.EntityFramework;

public class UserDal : GenericRepository<User>, IUserDal
{
    public List<OperationClaim> GetClaims(User user)
    {
        using (var context = new Context.Context())
        {
            var result = from operationClaim in context.OperationClaims
                join userOperationClaim in context.UserOperationClaims
                    on operationClaim.Id equals userOperationClaim.OperationClaimId
                where userOperationClaim.UserId == user.Id
                select new OperationClaim { Id = operationClaim.Id, Name = operationClaim.Name };
            return result.ToList();
        }
    }
}