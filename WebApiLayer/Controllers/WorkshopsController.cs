using BusinessLayer.Abstract;
using EntityLayer.Concrete;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace WebApiLayer.Controllers;

[Route("api/[controller]")]
[ApiController]
[Authorize]
public class WorkshopsController(IWorkshopService workshopService) : ControllerBase
{
    // why we use constructor methods?
    [HttpGet("getAllWorkshops")]
    [AllowAnonymous]
    public IActionResult GetAllWorkshops()
    {
        var result = workshopService.GetAll();
        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpGet("getWorkshopById/{id}")]
    [AllowAnonymous]
    public IActionResult GetWorkshopById(int id)
    {
        var result = workshopService.GetById(id);
        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpPost("addWorkshop")]
    public IActionResult AddEvent(Workshop workshop)
    {
        var result = workshopService.Add(workshop);
        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpPut("updateWorkshop/{id}")]
    public IActionResult UpdateWorkshop(int id, Workshop workshop)
    {
        workshop.Id = id;
        var result = workshopService.Update(workshop);
        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpDelete("deleteWorkshop/{id}")]
    public IActionResult DeleteWorkshop(int id)
    {
        var result = workshopService.Delete(id);
        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }

    [HttpGet("getWorkshopByName/{workshopName}")]
    [AllowAnonymous]
    public IActionResult GetWorkshopByName(string workshopName)
    {
        var result = workshopService.GetWorkshopByName(workshopName);
        if (result.Success)
        {
            return Ok(result);
        }

        return BadRequest(result);
    }
}