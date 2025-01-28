package com.example.gecekodubackend.core.dtos;

import com.example.gecekodubackend.entity.dtos.event.GetEventDto;
import com.example.gecekodubackend.entity.dtos.workshop.GetWorkshopDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDto {
    private int userId;

    private String firstName;

    private String lastName;

    private String email;

    private List<GetWorkshopDto> workshopDto;

    private List<GetEventDto> eventDto;
    
}
