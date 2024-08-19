using CoreLayer.Utilities.Results;
using EntityLayer.Concrete;

namespace BusinessLayer.Abstract;

public interface IUserService: IGenericService<User>
{
    IDataResult<User> GetUserByEmail(string email);

}