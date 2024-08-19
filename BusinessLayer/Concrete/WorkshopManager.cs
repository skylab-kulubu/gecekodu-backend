using BusinessLayer.Abstract;
using CoreLayer.Utilities.Results;
using DataAccessLayer.Abstract;
using EntityLayer.Concrete;
using BusinessLayer.Constants;

namespace BusinessLayer.Concrete;

public class WorkshopManager : IWorkshopService
{
    private IWorkshopDal _workshopDal;

    public WorkshopManager(IWorkshopDal workshopDal)
    {
        _workshopDal = workshopDal;
    }

    public IDataResult<Workshop> GetWorkshopByName(string workshopName)
    {
        var workshop = _workshopDal.Get(workshop => workshop.Name == workshopName);
        if (workshop == null)
        {
            return new ErrorDataResult<Workshop>(WorkshopMessages.WorkshopNotFound);
        }

        return new SuccessDataResult<Workshop>(workshop, WorkshopMessages.WorkshopBroughtSuccessfully);
    }

    private IResult CheckIfWorkshopExists(int workshopID)
    {
        var result = _workshopDal.Get(workshop => workshop.Id == workshopID);

        if (result == null)
        {
            return new ErrorResult(WorkshopMessages.WorkshopNotFound);
        }

        return new SuccessResult(WorkshopMessages.WorkshopAlreadyExists);
    }

    private IResult CheckIfNameExists(string workshopName)
    {
        var result = _workshopDal.Get(workshop => workshop.Name == workshopName);
        if (result == null)
        {
            return new ErrorResult(WorkshopMessages.WorkshopNotFound);
        }

        return new SuccessResult(WorkshopMessages.WorkshopAlreadyExists);
    }

    public IDataResult<Workshop> GetById(int id)
    {
        var result = _workshopDal.Get(workshop => workshop.Id == id);
        if (result == null)
        {
            return new ErrorDataResult<Workshop>(WorkshopMessages.WorkshopNotFound);
        }

        return new SuccessDataResult<Workshop>(result, WorkshopMessages.WorkshopBroughtSuccessfully);
    }

    public IDataResult<List<Workshop>> GetAll()
    {
        var result = _workshopDal.GetAll();
        if (result.Count == 0)
            return new ErrorDataResult<List<Workshop>>(WorkshopMessages.WorkshopsNotFound);
        return new SuccessDataResult<List<Workshop>>(result, WorkshopMessages.WorkshopsBroughtSuccessfully);
    }

    public IResult Add(Workshop p)
    {
        var result = CheckIfNameExists(p.Name);
        if (result.Success)
        {
            return new ErrorResult(result.Message);
        }

        _workshopDal.Insert(p);
        return new SuccessResult(WorkshopMessages.WorkshopAddedSuccessfully);
    }

    public IResult Update(Workshop p)
    {
        var result = GetById(p.Id);
        if (!result.Success)
        {
            return result;
        }

        _workshopDal.Update(p);
        return new SuccessResult(WorkshopMessages.WorkshopUpdatedSuccessfully);
    }

    public IResult Delete(int id)
    {
        var result = GetById(id);
        if (!result.Success)
        {
            return result;
        }

        _workshopDal.Delete(result.Data);
        return new SuccessResult(WorkshopMessages.WorkshopDeletedSuccessfully);
    }
}