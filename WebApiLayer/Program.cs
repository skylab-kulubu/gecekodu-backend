using System.Text;
using BusinessLayer.Abstract;
using BusinessLayer.Concrete;
using CoreLayer.DependencyReselvors;
using CoreLayer.Extensions;
using CoreLayer.Utilities.IoC;
using CoreLayer.Utilities.Security.JWT;
using DataAccessLayer.Abstract;
using DataAccessLayer.Concrete.EntityFramework;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using IdentityConstants = EntityLayer.Dtos.Roles.IdentityConstants;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();

// Add services to the container.
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

IServiceCollection services = null;

//Auth
var issuer = builder.Configuration["JwtConfig:Issuer"];
var audience = builder.Configuration["JwtConfig:Audience"];
var signingKey = builder.Configuration["JwtConfig:SigningKey"];
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme).AddJwtBearer(options =>
{
    options.TokenValidationParameters = new TokenValidationParameters
    {
        ValidateIssuer = true,
        ValidateAudience = true,
        ValidateLifetime = true,
        ValidateIssuerSigningKey = true,
        ValidIssuer = issuer,
        ValidAudience = audience,
        IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(signingKey))
    };
});

// ServiceTool.Create(builder.Services);

services.AddDependencyResolvers(new ICoreModule[]
{
    new CoreModule()
});


builder.Services.AddAuthorization(options =>
{
    options.AddPolicy(IdentityConstants.AdminUserPolicyName, p =>
        p.RequireClaim(IdentityConstants.AdminUserClaimName, "true"));
});

builder.Services.AddSingleton<IUserService, UserManager>();
builder.Services.AddSingleton<IUserDal, UserDal>();
builder.Services.AddSingleton<IEventService, EventManager>();
builder.Services.AddSingleton<IEventDal, EventDal>();
builder.Services.AddSingleton<IWorkshopService, WorkshopManager>();
builder.Services.AddSingleton<IWorkshopDal, WorkshopDal>();
builder.Services.AddSingleton<IAuthService, AuthManager>();
builder.Services.AddSingleton<ITokenHelper, JwtHelper>();


var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

//Authentication happens first
app.UseAuthentication();

//Authorization happens after authentication
app.UseAuthorization();

app.MapControllers();

app.Run();