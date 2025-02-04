package com.example.gecekodubackend.entity.dtos.workshop;

import com.example.gecekodubackend.core.dtos.GetUserDto;
import com.example.gecekodubackend.entity.concretes.Workshop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetWorkshopDto {

    private int workshopId;

    private String workshopName;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    private List<GetUserDto> userDtoList;

    public GetWorkshopDto(Workshop workshop){
        this.workshopId = workshop.getWorkshopId();
        this.workshopName = workshop.getWorkshopName();
        this.date = workshop.getDate();
        this.description = workshop.getDescription();
        this.userDtoList = new GetUserDto().buildListGetUserDto(workshop.getUsers().stream().toList());
    }

    public List<GetWorkshopDto> buildListGetWorkshopDto(List<Workshop> workshops){
        return workshops.stream()
                .map(GetWorkshopDto::new)
                .collect(Collectors.toList());
    }
}
