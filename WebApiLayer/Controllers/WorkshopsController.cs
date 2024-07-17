using BusinessLayer.Abstract;
using EntityLayer.Concrete;
using Microsoft.AspNetCore.Mvc;

namespace WebApiLayer.Controllers;

[Route("api/[controller]")]
[ApiController]
public class WorkshopsController : ControllerBase
{
    IWorkshopService _workshopService;

    // why we use constructor methods?
    public WorkshopsController(IWorkshopService workshopService)
    {
        _workshopService = workshopService;
    }

    [HttpGet("getAllWorkshops")]
    public IActionResult GetAllWorkshops()
    {
        var result = _workshopService.GetAllWorkshops();
        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpGet("getWorkshopById/{id}")]
    public IActionResult GetWorkshopById(int id)
    {
        var result = _workshopService.GetWorkshopById(id);
        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpPost("addWorkshop")]
    public IActionResult AddEvent(Workshop workshop)
    {
        var result = _workshopService.AddWorkshop(workshop);
        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpPut("updateWorkshop/{id}")]
    public IActionResult UpdateWorkshop(int id, Workshop workshop)
    {
        workshop.WorkshopID = id;
        var result = _workshopService.UpdateWorkshop(workshop);
        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpDelete("deleteWorkshop/{id}")]
    public IActionResult DeleteWorkshop(int id)
    {
        var result = _workshopService.DeleteWorkshop(id);
        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpGet("getWorkshopByName/{workshopName}")]
    public IActionResult GetWorkshopByName(string workshopName)
    {
        var result = _workshopService.GetWorkshopByName(workshopName);
        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }
}