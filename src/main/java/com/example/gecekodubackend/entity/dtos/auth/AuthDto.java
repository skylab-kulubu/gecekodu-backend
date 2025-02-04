package com.example.gecekodubackend.entity.dtos.auth;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthDto {
    private String email;
    private String password;
}
