package com.example.gecekodubackend.business.abstracts;

import com.example.gecekodubackend.core.dtos.CreateUserDto;
import com.example.gecekodubackend.core.dtos.GetUserDto;
import com.example.gecekodubackend.core.utilities.results.DataResult;
import com.example.gecekodubackend.core.utilities.results.Result;
import com.example.gecekodubackend.core.entities.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    DataResult<List<GetUserDto>> getAllUsers();

    DataResult<GetUserDto> getUserById(int id);

    Result addUser(CreateUserDto createUserDto);

    Result updateUser(CreateUserDto userDto, int id);

    Result deleteUser(int id);

    DataResult<GetUserDto> getUserByEmail(String email);

    DataResult<User> getUserEntityByEmail(String email);

    DataResult<User> getUserEntityById(int id);

    Result addUserToEvent(int userId, int eventId);

    Result addModerator(int id);

    Result removeModerator(int id);
}
