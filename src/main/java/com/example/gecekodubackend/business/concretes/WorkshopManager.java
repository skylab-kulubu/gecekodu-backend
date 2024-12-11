package com.example.gecekodubackend.business.concretes;

import com.example.gecekodubackend.business.abstracts.WorkshopService;
import com.example.gecekodubackend.business.constants.WorkshopMessages;
import com.example.gecekodubackend.core.utilities.results.*;
import com.example.gecekodubackend.dataAccess.abstracts.WorkshopDao;
import com.example.gecekodubackend.entity.concretes.Workshop;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkshopManager implements WorkshopService {

    private final WorkshopDao workshopDao;

    public WorkshopManager(WorkshopDao workshopDao) {
        super();
        this.workshopDao = workshopDao;
    }

    @Override
    public DataResult<List<Workshop>> getAllWorkshops() {
        List<Workshop> result = workshopDao.findAll();

        if(result.isEmpty()){
            return new ErrorDataResult<>(WorkshopMessages.workshopsNotFound);
        }

        return new SuccessDataResult<>(result, WorkshopMessages.workshopsBroughtSuccessfully);
    }

    @Override
    public DataResult<Workshop> getWorkshopById(int id) {
        var workshop = workshopDao.findById(id);

        if(workshop.isEmpty()){
            return new ErrorDataResult<>(WorkshopMessages.workshopNotFound);
        }

        return new SuccessDataResult<>(workshop.get(), WorkshopMessages.workshopBroughtSuccessfully);
    }

    @Override
    public Result addWorkshop(Workshop workshop) {
        var result = checkIfTheWorkshopExists(workshop.getWorkshopId());

        if(result.isSuccess()){
            return new ErrorResult(WorkshopMessages.workshopAlreadyExists);
        }

        workshopDao.save(workshop);
        return new SuccessResult(WorkshopMessages.workshopAddedSuccessfully);
    }

    @Override
    public Result updateWorkshop(Workshop workshop, int id) {
        var result = checkIfTheWorkshopExists(workshop.getWorkshopId());

        if (result == null){
            return new ErrorResult(WorkshopMessages.workshopNotFound);
        }

        DataResult<Workshop> workshopToUpdate = getWorkshopById(id);
        workshopToUpdate.getData().setWorkshopName(workshop.getWorkshopName());
        workshopToUpdate.getData().setDescription(workshop.getDescription());
        workshopToUpdate.getData().setDate(workshop.getDate());
        workshopDao.save(workshopToUpdate.getData());

        return new SuccessResult(WorkshopMessages.workshopUpdatedSuccessfully);
    }

    @Override
    public Result deleteWorkshop(int id) {
        var workshop = workshopDao.findById(id);

        if(workshop.isEmpty()){
            return new ErrorResult(WorkshopMessages.workshopNotFound);
        }

        workshopDao.deleteById(id);
        return new SuccessResult(WorkshopMessages.workshopDeletedSuccessfully);
    }

    public Result checkIfTheWorkshopExists(int id){
        Optional<Workshop> workshop = workshopDao.findById(id);

        if(workshop.isEmpty()){
            return new ErrorResult(WorkshopMessages.workshopNotFound);
        }

        return new SuccessResult(WorkshopMessages.workshopAlreadyExists);
    }
}
