package com.example.gecekodubackend.core.dtos;

import com.example.gecekodubackend.entity.concretes.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateUserDto {

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private Role role;
}
