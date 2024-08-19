namespace EntityLayer.Dtos.Roles;

public class IdentityConstants
{
    //This class is used to store the constants related to the Identity
    //We store the claim name, policy name and role names here
    //This way we can avoid magic strings in our code
    
    //Claim is for the token, policy is for the authorization
    public const string AdminUserClaimName = "admin";
    public const string AdminUserPolicyName = "Admin";
    public const string User = "User";
    public const string WorkshopOwner = "WorkshopOwner";
    public const string EventOwner = "EventOwner";
}