using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using CoreLayer.Extensions;
using CoreLayer.Utilities.Security.Encryption;
using EntityLayer.Concrete;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;

namespace CoreLayer.Utilities.Security.JWT;

public class JwtHelper : ITokenHelper
{
    private IConfiguration Configuration { get; } // this helps us to reach the app settings file for the token
    private TokenOptions _tokenOptions;
    private DateTime _accessTokenExpiration;

    public
        JwtHelper(IConfiguration configuration) //this is the constructor. It is used to get the token options from the app settings file
    {
        Configuration = configuration;
        _tokenOptions = Configuration.GetSection("TokenOptions").Get<TokenOptions>();
    }

    public AccessToken CreateToken(User user, List<OperationClaim> operationClaims)
    {
        _accessTokenExpiration = DateTime.Now.AddMinutes(_tokenOptions.AccessTokenExpiration);
        var securityKey = SecurityKeyHelper.CreateSecurityKey(_tokenOptions.SecurityKey);
        var signingCredentials = SigningCredentialsHelper.CreateSigningCredentials(securityKey);
        var jwt = CreateJwtSecurityToken(_tokenOptions, user, signingCredentials, operationClaims);
        var jwtSecurityTokenHandler = new JwtSecurityTokenHandler();
        var token = jwtSecurityTokenHandler.WriteToken(jwt);

        return new AccessToken
        {
            Token = token,
            Expiration = _accessTokenExpiration
        };
    }

    private JwtSecurityToken CreateJwtSecurityToken(TokenOptions tokenOptions, User user,
        SigningCredentials signingCredentials, List<OperationClaim> operationClaims)
    {
        var jwt = new JwtSecurityToken(
            issuer: tokenOptions.Issuer,
            audience: tokenOptions.Audience,
            expires: _accessTokenExpiration,
            notBefore: DateTime.Now,
            claims: SetClaims(user, operationClaims),
            signingCredentials: signingCredentials
        );
        return jwt;
    }

    private IEnumerable<Claim> SetClaims(User user, List<OperationClaim> operationClaims)
    {
        var claims = new List<Claim>();
        claims.AddNameIdentifier(user.Id.ToString());
        claims.AddEmail(user.Email);
        claims.AddName($"{user.FirstName} {user.LastName}"); // '$' sign allows us to write c# code in the string
        claims.AddRoles(operationClaims.Select(c => c.Name).ToArray());

        return claims;
    }
}