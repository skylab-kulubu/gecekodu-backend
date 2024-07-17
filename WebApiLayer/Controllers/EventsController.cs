using BusinessLayer.Abstract;
using EntityLayer.Concrete;
using Microsoft.AspNetCore.Mvc;

namespace WebApiLayer.Controllers;

[Route("api/[controller]")]
[ApiController]
public class EventsController : ControllerBase
{
    IEventService _eventService;

    public EventsController(IEventService eventService)
    {
        _eventService = eventService;
    }

    [HttpGet("getAllEvents")]
    public IActionResult GetAllEvents()
    {
        var result = _eventService.GetAllEvents();

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpGet("getById/{id}")]
    public IActionResult GetEventById(int id)
    {
        var result = _eventService.GetEventById(id);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpPost("addEvent")]
    public IActionResult AddEvent(Event @event)
    {
        var result = _eventService.AddEvent(@event);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpPut("updateEvent/{id}")]
    public IActionResult UpdateEvent(int id, Event @event)
    {
        @event.EventID = id;
        var result = _eventService.UpdateEvent(@event);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpDelete("deleteEvent/{id}")]
    public IActionResult DeleteEvent(int id)
    {
        var result = _eventService.DeleteEvent(id);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpGet("getEventByName/{name}")]
    public IActionResult GetEventByName(string name)
    {
        var result = _eventService.GetEventByName(name);

        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }
}