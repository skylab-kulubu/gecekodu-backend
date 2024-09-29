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

// Swagger/OpenAPI ayarlarý
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// JWT Authentication
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

// Baðýmlýlýk çözücülerini ekleyin
builder.Services.AddDependencyResolvers(new ICoreModule[]
{
    new CoreModule()
});

// Yetkilendirme
builder.Services.AddAuthorization(options =>
{
    options.AddPolicy(IdentityConstants.AdminUserPolicyName, p =>
        p.RequireClaim(IdentityConstants.AdminUserClaimName, "true"));
});

// Servisler
builder.Services.AddSingleton<IUserService, UserManager>();
builder.Services.AddSingleton<IUserDal, UserDal>();
builder.Services.AddSingleton<IEventService, EventManager>();
builder.Services.AddSingleton<IEventDal, EventDal>();
builder.Services.AddSingleton<IWorkshopService, WorkshopManager>();
builder.Services.AddSingleton<IWorkshopDal, WorkshopDal>();
builder.Services.AddSingleton<IAuthService, AuthManager>();
builder.Services.AddSingleton<ITokenHelper, JwtHelper>();

var app = builder.Build();

// Geliþtirme ortamýnda Swagger kullanýmý
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

// Authentication ve Authorization middleware sýralamasý
app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();

app.Run();
