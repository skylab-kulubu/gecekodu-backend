package com.example.gecekodubackend.core.dtos;

import com.example.gecekodubackend.entity.concretes.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateUserDto {

    @NotEmpty(message = "Kullanıcı adı boş olamaz!")
    @Size(max = 30, min = 3, message = "En az 3, en fazla 30 karakter girebilirsiniz!")
    private String firstName;

    @NotEmpty(message = "Kullanıcı soy adı boş olamaz!")
    @Size(max = 30, min = 3, message = "En az 3, en fazla 30 karakter girebilirsiniz!")
    private String lastName;

    @Size(min = 8, max = 30, message = "Girdiğiniz şifre en az 8, en çok 30 haneli olmalıdır!")
    @NotEmpty(message = "Şifre alanı boş olamaz!")
    private String password;

    @NotEmpty(message = "Email adresiniz boş olamaz!")
    @Email(message = "Girdiğiniz adres email formatına uygun değil!")
    @Size(max = 40, min = 11, message = "En az 11, en fazla 40 karakter girebilirsiniz!")
    private String email;

    private Role role;
}
