namespace EntityLayer.Dtos.User;

public class UserDto
{
    public int Id { get; set; }

    public string FirstName { get; set; }=String.Empty;

    public string LastName { get; set; }=String.Empty;

    public string Email { get; set; } = String.Empty;
}