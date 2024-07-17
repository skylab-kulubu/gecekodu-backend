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

    public IDataResult<List<Workshop>> GetAllWorkshops()
    {
        return new SuccessDataResult<List<Workshop>>(_workshopDal.GetAll(),
            WorkshopMessages.WorkshopsBroughtSuccessfully);
    }

    public IDataResult<Workshop> GetWorkshopById(int workshopID)
    {
        var workshop = _workshopDal.Get(workshop => workshop.WorkshopID == workshopID);
        if (workshop == null)
        {
            return new ErrorDataResult<Workshop>(WorkshopMessages.WorkshopNotFound);
        }

        return new SuccessDataResult<Workshop>(workshop, WorkshopMessages.WorkshopBroughtSuccessfully);
    }

    public IResult AddWorkshop(Workshop workshop)
    {
        var workshopResult = GetWorkshopById(workshop.WorkshopID);
        if (workshopResult.Success)
        {
            return new ErrorResult(WorkshopMessages.WorkshopAlreadyExists);
        }

        _workshopDal.Insert(workshop);
        return new SuccessResult(WorkshopMessages.WorkshopAddedSuccessfully);
    }

    public IResult UpdateWorkshop(Workshop workshop)
    {
        var workshopResult = GetWorkshopById(workshop.WorkshopID);
        if (!workshopResult.Success)
        {
            return workshopResult;
        }

        _workshopDal.Update(workshop);
        return new SuccessResult(WorkshopMessages.WorkshopUpdatedSuccessfully);
    }

    public IResult DeleteWorkshop(int workshopID)
    {
        var workshopResult = GetWorkshopById(workshopID);
        if (!workshopResult.Success)
        {
            return workshopResult;
        }

        _workshopDal.Delete(workshopResult.Data);
        return new SuccessResult(WorkshopMessages.WorkshopDeletedSuccessfully);
    }

    public IDataResult<Workshop> GetWorkshopByName(string workshopName)
    {
        var workshop = _workshopDal.Get(workshop => workshop.WorkshopName == workshopName);
        if (workshop == null)
        {
            return new ErrorDataResult<Workshop>(WorkshopMessages.WorkshopNotFound);
        }

        return new SuccessDataResult<Workshop>(workshop, WorkshopMessages.WorkshopBroughtSuccessfully);
    }

    private IResult CheckIfWorkshopExists(int workshopID)
    {
        var result = _workshopDal.Get(workshop => workshop.WorkshopID == workshopID);

        if (result == null)
        {
            return new ErrorResult(WorkshopMessages.WorkshopNotFound);
        }

        return new SuccessResult(WorkshopMessages.WorkshopAlreadyExists);
    }

    private IResult CheckIfNameExists(string workshopName)
    {
        var result = _workshopDal.Get(workshop => workshop.WorkshopName == workshopName);
        if (result == null)
        {
            return new ErrorResult(WorkshopMessages.WorkshopNotFound);
        }

        return new SuccessResult(WorkshopMessages.WorkshopAlreadyExists);
    }
}