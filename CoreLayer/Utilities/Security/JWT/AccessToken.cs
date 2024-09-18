namespace CoreLayer.Utilities.Security.JWT;

public class AccessToken //this class is an access key for the user
{
    public string Token { get; set; } //this is the token
    
    public DateTime Expiration { get; set; } //this is the expiration date of the token
}