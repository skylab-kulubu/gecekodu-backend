package com.example.gecekodubackend.business.abstracts;

import com.example.gecekodubackend.core.utilities.results.DataResult;
import com.example.gecekodubackend.core.utilities.results.Result;
import com.example.gecekodubackend.entity.concretes.Workshop;
import com.example.gecekodubackend.entity.dtos.workshop.CreateWorkshopDto;
import com.example.gecekodubackend.entity.dtos.workshop.GetWorkshopDto;

import java.util.List;

public interface WorkshopService {
    DataResult<List<GetWorkshopDto>> getAllWorkshops();

    DataResult<GetWorkshopDto> getWorkshopById(int id);

    Result addWorkshop(CreateWorkshopDto createWorkshopDto);

    Result updateWorkshop(CreateWorkshopDto createWorkshopDto, int id);

    Result deleteWorkshop(int id);

    DataResult<Workshop> getWorkshopEntityById(int id);

    DataResult<GetWorkshopDto> getWorkshopByName(String workshopName);
}
