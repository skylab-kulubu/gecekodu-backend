using CoreLayer.Utilities.Results;
using EntityLayer.Concrete;
using EntityLayer.Dtos;

namespace BusinessLayer.Abstract;

public interface IUserService
{

    IDataResult<List<User>> GetAllUsers();
    
    IDataResult<User> GetUserById(int userID);
    
    IResult AddUser(User user);
    
    IResult UpdateUser(User user);
    
    IResult DeleteUser(int userID);
    
    IDataResult<User> GetUserByEmail(string email);

}