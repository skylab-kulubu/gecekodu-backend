package com.example.gecekodubackend.core.dataAccess;

import com.example.gecekodubackend.core.dtos.GetUserDto;
import com.example.gecekodubackend.core.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User getUserByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUserId(int userId);


}
