using CoreLayer.Utilities.IoC;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.DependencyInjection;

namespace CoreLayer.DependencyReselvors;

public class CoreModule:ICoreModule
{
    public void Load(IServiceCollection serviceCollection)
    {
        serviceCollection.AddSingleton<IHttpContextAccessor, HttpContextAccessor>();
    }
}