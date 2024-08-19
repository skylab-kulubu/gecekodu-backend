using CoreLayer.Utilities.Results;
using EntityLayer.Concrete;

namespace BusinessLayer.Abstract;

public interface IWorkshopService: IGenericService<Workshop>
{
    IDataResult<Workshop> GetWorkshopByName(string workshopName);
}