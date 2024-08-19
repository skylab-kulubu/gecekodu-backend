using EntityLayer.Concrete;
using EntityLayer.Dtos.User;

namespace EntityLayer.Mappers;

public static class UserMappers
{
    public static UserDto ToUserDto(this User user)
    {
        return new UserDto
        {
            //We just map the properties of the User which we want to expose to the client
            Id = user.Id,
            FirstName = user.FirstName,
            LastName = user.LastName,
            Email = user.Email
        };
    }
}