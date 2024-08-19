using BusinessLayer.Abstract;
using BusinessLayer.Constants;
using CoreLayer.Utilities.Results;
using DataAccessLayer.Abstract;
using EntityLayer.Concrete;
using EntityLayer.Dtos;

namespace BusinessLayer.Concrete;

public class UserManager : IUserService
{
    private IUserDal _userDal;

    public UserManager(IUserDal userDal)
    {
        _userDal = userDal;
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
        var result = _userDal.Get(user => user.Id == userID);

        if (result == null)
        {
            return new ErrorResult(UserMessages.UserNotFound);
        }

        return new SuccessResult(UserMessages.UserAlreadyExists);
    }

    public IDataResult<User> GetById(int id)
    {
        var result = _userDal.Get(user => user.Id == id);
        if (result == null)
        {
            return new ErrorDataResult<User>(UserMessages.UserNotFound);
        }

        return new SuccessDataResult<User>(result, UserMessages.UserBroughtSuccessfully);
    }

    public IDataResult<List<User>> GetAll()
    {
        var result = _userDal.GetAll();
        if (result.Count == 0)
        {
            return new ErrorDataResult<List<User>>(UserMessages.UsersNotFound);
        }

        return new SuccessDataResult<List<User>>(result, UserMessages.UsersBroughtSuccessfully);
    }

    public IResult Add(User p)
    {
        var result = CheckIfEmailExists(p.Email);
        if (result.Success)
        {
            return new ErrorResult(UserMessages.EmailAlreadyExists);
        }

        _userDal.Insert(p);
        return new SuccessResult(UserMessages.UserAddedSuccessfully);
    }

    public IResult Update(User p)
    {
        var result = GetById(p.Id);
        if (!result.Success)
        {
            return result;
        }

        _userDal.Update(p);
        return new SuccessResult(UserMessages.UserUpdatedSuccessfully);
    }

    public IResult Delete(int id)
    {
        var result = GetById(id);
        if (!result.Success)
        {
            return result;
        }

        _userDal.Delete(result.Data);
        return new SuccessResult(UserMessages.UserDeletedSuccessfully);
    }
}