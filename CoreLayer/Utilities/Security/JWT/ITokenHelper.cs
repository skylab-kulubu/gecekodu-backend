using EntityLayer.Concrete;

namespace CoreLayer.Utilities.Security.JWT;

public interface ITokenHelper //this interface is added in case of need to change the token helper or for testing purposes or for chancing the technique
{
    AccessToken CreateToken(User user, List<OperationClaim> operationClaims); //this method is used to create a token
}