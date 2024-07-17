using BusinessLayer.Abstract;
using BusinessLayer.Concrete;
using DataAccessLayer.Abstract;
using DataAccessLayer.Concrete.EntityFramework;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();

// Add services to the container.
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();


builder.Services.AddSingleton<IUserService, UserManager>();
builder.Services.AddSingleton<IUserDal, UserDal>();
builder.Services.AddSingleton<IEventService, EventManager>();
builder.Services.AddSingleton<IEventDal, EventDal>();
builder.Services.AddSingleton<IWorkshopService, WorkshopManager>();
builder.Services.AddSingleton<IWorkshopDal, WorkshopDal>();


var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.MapControllers();

app.Run();