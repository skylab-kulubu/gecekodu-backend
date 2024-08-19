using BusinessLayer.Abstract;
using EntityLayer.Concrete;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace WebApiLayer.Controllers;

[Route("api/[controller]")]
[ApiController]
[Authorize]
public class EventsController(IEventService eventService) : ControllerBase
{
    [HttpGet("getAllEvents")]
    [AllowAnonymous]
    public IActionResult GetAllEvents()
    {
        var result = eventService.GetAll();

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpGet("getById/{id}")]
    [AllowAnonymous]
    public IActionResult GetEventById(int id)
    {
        var result = eventService.GetById(id);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpPost("addEvent")]
    public IActionResult AddEvent(Event @event)
    {
        var result = eventService.Add(@event);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpPut("updateEvent/{id}")]
    public IActionResult UpdateEvent(int id, Event @event)
    {
        @event.Id = id;
        var result = eventService.Update(@event);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpDelete("deleteEvent/{id}")]
    public IActionResult DeleteEvent(int id)
    {
        var result = eventService.Delete(id);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpGet("getEventByName/{name}")]
    [AllowAnonymous]
    public IActionResult GetEventByName(string name)
    {
        var result = eventService.GetEventByName(name);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }
}