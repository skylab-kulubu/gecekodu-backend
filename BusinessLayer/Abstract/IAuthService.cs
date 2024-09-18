using EntityLayer.Concrete;
using CoreLayer.Utilities.Results;
using CoreLayer.Utilities.Security.JWT;
using EntityLayer.Dtos.User;

namespace BusinessLayer.Abstract;

public interface IAuthService
{
    IDataResult<User> Register(UserRegisterDto userRegisterDto, string password);
    IDataResult<User> Login(UserLoginDto userLoginDto);
    IResult UserExists(string email);
    IDataResult<AccessToken> CreateAccessToken(User user);
}