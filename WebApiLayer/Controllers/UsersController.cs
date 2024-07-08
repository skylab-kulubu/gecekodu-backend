using BusinessLayer.Abstract;
using EntityLayer.Concrete;
using EntityLayer.Dtos;
using Microsoft.AspNetCore.Mvc;

namespace WebApiLayer.Controllers;


[Route("api/[controller]")]
[ApiController]
public class UsersController: ControllerBase
{ 
    IUserService _userService;

    public UsersController(IUserService userService)
    {
        _userService = userService;
    }
    
    
    
    [HttpGet("getAllUsers")]
    public IActionResult GetAllUsers()
    {
       var result = _userService.GetAllUsers();

       if (result.Success)
       {
           return Ok(result);
       }

       return BadRequest(result);
    }
    
    [HttpGet("getById/{id}")]
    public IActionResult GetUserById(int id)
    {
        var result = _userService.GetUserById(id);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }
    
    [HttpPost("addUser")]
    public IActionResult AddUser(User user)
    {
        var result = _userService.AddUser(user);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }
    
    [HttpPut("updateUser/{id}")]
    public IActionResult UpdateUser(int id, User user)
    {
        user.ID = id;
        var result = _userService.UpdateUser(user);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }
    
    [HttpDelete("deleteUser/{id}")]
    public IActionResult DeleteUser(int id)
    {
        var result = _userService.DeleteUser(id);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }
    
    [HttpGet("getUserByEmail/{email}")]
    public IActionResult GetUserByEmail(string email)
    {
        var result = _userService.GetUserByEmail(email);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }
    
}