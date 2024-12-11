package com.example.gecekodubackend.entity.dtos.workshop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetWorkshopDto {
    private int workshopId;

    private String workshopName;
}
