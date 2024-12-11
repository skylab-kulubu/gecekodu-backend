package com.example.gecekodubackend.business.abstracts;

import com.example.gecekodubackend.core.dtos.GetUserDto;
import com.example.gecekodubackend.core.utilities.results.DataResult;
import com.example.gecekodubackend.core.utilities.results.Result;
import com.example.gecekodubackend.core.entities.*;

import java.util.List;

public interface UserService {
    DataResult<List<GetUserDto>> getAllUsers();
    DataResult<GetUserDto> getUserById(int id);
    Result addUser(User user);
    Result updateUser(GetUserDto userDto, int id);
    Result deleteUser(int id);
    DataResult<User> getUserByEmail(String email);
}
