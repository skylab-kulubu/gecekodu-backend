package com.example.gecekodubackend.entity.dtos.event;

import com.example.gecekodubackend.core.dtos.GetUserDto;
import com.example.gecekodubackend.entity.concretes.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEventDto {
    private int eventId;

    private String eventName;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    private List<GetUserDto> userDtoList;

    public GetEventDto(Event event){
        this.eventId = event.getEventId();
        this.eventName = event.getEventName();
        this.date = event.getDate();
        this.description = event.getDescription();
        this.userDtoList = new GetUserDto().buildListGetUserDto(event.getUsers().stream().toList());
    }

    public List<GetEventDto> buildListGetEvent(List<Event> events){
        return events.stream()
                .map(GetEventDto::new)
                .collect(Collectors.toList());
    }
}
