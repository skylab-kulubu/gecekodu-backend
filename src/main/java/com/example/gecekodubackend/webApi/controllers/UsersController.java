package com.example.gecekodubackend.webApi.controllers;

import com.example.gecekodubackend.business.abstracts.UserService;
import com.example.gecekodubackend.core.dtos.GetUserDto;
import com.example.gecekodubackend.core.utilities.results.DataResult;
import com.example.gecekodubackend.core.utilities.results.Result;
import com.example.gecekodubackend.core.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("webApi/users")
public class UsersController {

    //Due to the final keyword, this object will be assigned a value by the constructor method.
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService){
        super();
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public DataResult<List<User>> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }

    @GetMapping("/getUserById/{id}")
    public DataResult<GetUserDto> getUserById(@PathVariable(name = "id") Integer userId){
        return this.userService.getUserById(userId);
    }

    @PutMapping("/updateUser/{id}")
    public Result updateUser(@RequestBody User user, @PathVariable int id){
        return this.userService.updateUser(user, id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public Result deleteUser(@PathVariable int id){
        return this.userService.deleteUser(id);
    }

    @GetMapping("/getUserByEmail/{email}")
    public DataResult<GetUserDto> getUserByEmail(@PathVariable(name = "email") String email){
        var result = userService.getUserByEmail(email);

        if(result == null){
            return ResponseEntity.ok(result).getBody();
        }
        return ResponseEntity.badRequest().body(result).getBody();
    }
}
