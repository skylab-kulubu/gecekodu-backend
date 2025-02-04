package com.example.gecekodubackend.entity.dtos.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateEventDto {

    private String eventName;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
}
