using CoreLayer.Extensions;
using CoreLayer.Utilities.IoC;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.DependencyInjection;
/*
namespace BusinessLayer.BusinessAspects.Autofac;

public class _SecuredOperation // :MethodInterception
{
private string[] _roles;
private IHttpContextAccessor _httpContextAccessor;

public SecuredOperation(string roles)
{
_roles = roles.Split(',');
_httpContextAccessor = ServiceTool.ServiceProvider.GetService<IHttpContextAccessor>();
}

protected override void OnBefore(IInvocation invocation)
{
var roleClaims = _httpContextAccessor.HttpContext.User.ClaimRoles();
foreach (var role in _roles)
{
    if (roleClaims.Contains(role))
    {
        return;
    }
}

throw new Exception(Messages.AuthorizationDenied);
}

}
*/
// This class is used to check if the user has the required roles to access a method.