package com.example.gecekodubackend.core.dtos;

import com.example.gecekodubackend.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetUserForEventDto {
    private int userId;

    private String firstName;

    private String lastName;


    public GetUserForEventDto(User user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public List<GetUserForEventDto> buildListGetUserForEventDto(List<User> users) {
        return users.stream()
                .map(GetUserForEventDto::new)
                .collect(Collectors.toList());
    }
}
