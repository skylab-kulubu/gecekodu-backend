package com.example.gecekodubackend.business.abstracts;

import com.example.gecekodubackend.core.utilities.results.DataResult;
import com.example.gecekodubackend.core.utilities.results.Result;
import com.example.gecekodubackend.entity.concretes.Workshop;

import java.util.List;

public interface WorkshopService {
    DataResult<List<Workshop>> getAllWorkshops();

    DataResult<Workshop> getWorkshopById(int id);

    Result addWorkshop(Workshop workshop);

    Result updateWorkshop(Workshop workshop,int id);

    Result deleteWorkshop(int id);


}
