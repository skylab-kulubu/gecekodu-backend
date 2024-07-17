using CoreLayer.Utilities.Results;
using EntityLayer.Concrete;

namespace BusinessLayer.Abstract;

public interface IWorkshopService
{
    IDataResult<List<Workshop>> GetAllWorkshops();

    IDataResult<Workshop> GetWorkshopById(int workshopID);

    IResult AddWorkshop(Workshop workshop);

    IResult UpdateWorkshop(Workshop workshop);

    IResult DeleteWorkshop(int workshopID);

    IDataResult<Workshop> GetWorkshopByName(string workshopName);
}