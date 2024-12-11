package com.example.gecekodubackend.core.dataAccess;

import com.example.gecekodubackend.core.dtos.GetUserDto;
import com.example.gecekodubackend.core.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    GetUserDto getUserByEmail(String email);
}
