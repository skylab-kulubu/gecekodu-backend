using BusinessLayer.Abstract;
using EntityLayer.Dtos.User;
using Microsoft.AspNetCore.Mvc;

namespace WebApiLayer.Controllers;

[Route("api/[controller]")]
[ApiController]
public class AuthController : ControllerBase
{
    private IAuthService _authService;

    public AuthController(IAuthService authService)
    {
        _authService = authService;
    }

    [HttpPost("login")]
    public ActionResult Login(UserLoginDto userForLoginDto)
    {
        var userToLogin = _authService.Login(userForLoginDto);
        if (!userToLogin.Success)
        {
            return BadRequest(userToLogin.Message);
        }

        var result = _authService.CreateAccessToken(userToLogin.Data);
        if (result.Success)
        {
            return Ok(result.Data);
        }

        return BadRequest(result.Message);
    }

    [HttpPost("register")]
    public ActionResult Register(UserRegisterDto userForRegisterDto)
    {
        var userExists = _authService.UserExists(userForRegisterDto.Email);
        if (!userExists.Success)
        {
            return BadRequest(userExists.Message);
        }

        var registerResult = _authService.Register(userForRegisterDto, userForRegisterDto.Password);
        var result = _authService.CreateAccessToken(registerResult.Data);
        if (result.Success)
        {
            return Ok(result.Data);
        }

        return BadRequest(result.Message);
    }
}