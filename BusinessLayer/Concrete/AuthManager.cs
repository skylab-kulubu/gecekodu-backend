using BusinessLayer.Abstract;
using EntityLayer.Concrete;
using CoreLayer.Utilities.Results;
using CoreLayer.Utilities.Security.Hashing;
using CoreLayer.Utilities.Security.JWT;
using EntityLayer.Dtos.User;
using BusinessLayer.Constants;

namespace BusinessLayer.Concrete;

public class AuthManager(IUserService userService, ITokenHelper tokenHelper) : IAuthService
{
    public IDataResult<User> Register(UserRegisterDto userRegisterDto, string password)
    {
        byte[] passwordHash, passwordSalt;
        HashingHelper.CreatePasswordHash(password, out passwordHash, out passwordSalt);
        var user = new User
        {
            Email = userRegisterDto.Email,
            FirstName = userRegisterDto.FirstName,
            LastName = userRegisterDto.LastName,
            PasswordHash = passwordHash,
            PasswordSalt = passwordSalt,
        };
        userService.Add(user);
        return new SuccessDataResult<User>(user, AuthMessages.UserRegistered);
    }

    public IDataResult<User> Login(UserLoginDto userLoginDto)
    {
        var userToCheck = userService.GetUserByEmail(userLoginDto.Email).Data;
        if (userToCheck == null)
        {
            return new ErrorDataResult<User>(AuthMessages.UserNotFound);
        }

        if (!HashingHelper.VerifyPasswordHash(userLoginDto.Password, userToCheck.PasswordHash,
                userToCheck.PasswordSalt))
        {
            return new ErrorDataResult<User>(AuthMessages.PasswordError);
        }

        return new SuccessDataResult<User>(userToCheck, AuthMessages.SuccessfulLogin);
    }

    public IResult UserExists(string email)
    {
        if (userService.GetUserByEmail(email) != null)
        {
            return new ErrorResult(AuthMessages.UserAlreadyExists);
        }

        return new SuccessResult();
    }

    public IDataResult<AccessToken> CreateAccessToken(User user)
    {
        var claims = userService.GetClaims(user);
        var accessToken = tokenHelper.CreateToken(user, claims);
        return new SuccessDataResult<AccessToken>(accessToken, AuthMessages.AccessTokenCreated);
    }
}