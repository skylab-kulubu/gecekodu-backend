using BusinessLayer.Abstract;
using BusinessLayer.Constants;
using CoreLayer.Utilities.Results;
using DataAccessLayer.Abstract;
using EntityLayer.Concrete;
using EntityLayer.Dtos;

namespace BusinessLayer.Concrete;

public class UserManager:IUserService
{
    private IUserDal _userDal;

    public UserManager(IUserDal userDal)
    {
        _userDal = userDal;
    }


    public IDataResult<List<User>> GetAllUsers()
    {
        return new SuccessDataResult<List<User>>(_userDal.GetAll(), UserMessages.UsersBroughtSuccessfully);
    }

    public IDataResult<User> GetUserById(int userID)
    {
        var user = _userDal.Get(user => user.ID == userID);

        if (user == null)
        {
            return new ErrorDataResult<User>(UserMessages.UserNotFound);
        }
        
        return new SuccessDataResult<User>(user, UserMessages.UserBroughtSuccessfully);
    }

    public IResult AddUser(User user)
    {
        
        //this is a basic example of how businessLayer can be used to check if email already exists
        //we will be refactoring this later
       var emailResult = CheckIfEmailExists(user.Email);
       if (emailResult.Success)
       {
           return new ErrorResult(emailResult.Message);
       }
       
       _userDal.Insert(user);

       return new SuccessResult(UserMessages.UserAddedSuccessfully);
    }

    public IResult UpdateUser(User user)
    {
        //this is a basic example of how businessLayer can be used to check if user exists
        //we will be refactoring this later
        var userResult = GetUserById(user.ID);
        if(!userResult.Success)
        {
            return userResult;
        }
        
        _userDal.Update(user);
        
        return new SuccessResult(UserMessages.UserUpdatedSuccessfully);
    }

    public IResult DeleteUser(int userID)
    {
        //this is a basic example of how businessLayer can be used to check if user exists
        //we will be refactoring this later
        var userResult = GetUserById(userID);
        if(!userResult.Success)
        {
            return userResult;
        }
        
        _userDal.Delete(userResult.Data);
        
        return new SuccessResult(UserMessages.UserDeletedSuccessfully);
    }

    public IDataResult<User> GetUserByEmail(string email)
    {
        var user = _userDal.Get(user => user.Email == email);

        if (user == null)
        {
            return new ErrorDataResult<User>(UserMessages.UserNotFound);
        }
        
        return new SuccessDataResult<User>(user, UserMessages.UserBroughtSuccessfully);
    }
    
    
    private IResult CheckIfEmailExists(string email)
    {
        var result = _userDal.Get(user => user.Email == email);
        
        if (result == null)
        {
            return new ErrorResult(UserMessages.UserNotFound);
        }
        
        return new SuccessResult(UserMessages.EmailAlreadyExists);
    }
    
    private IResult CheckIfUserExists(int userID)
    {
        var result = _userDal.Get(user => user.ID == userID);
        
        if (result == null)
        {
            return new ErrorResult(UserMessages.UserNotFound);
        }
        
        return new SuccessResult(UserMessages.UserAlreadyExists);
    }
}