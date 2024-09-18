using CoreLayer.Utilities.Results;
using EntityLayer.Concrete;

namespace BusinessLayer.Abstract;

public interface IUserService : IGenericService<User>
{
    IDataResult<User> GetById(int id);
    IDataResult<List<User>> GetAll();
    IResult Add(User user);
    IResult Update(User user);
    IResult Delete(int id);
    IDataResult<User> GetUserByEmail(string email);
    List<OperationClaim> GetClaims(User user);
}