package com.example.gecekodubackend.webApi.controllers;

import com.example.gecekodubackend.business.abstracts.UserService;
import com.example.gecekodubackend.core.dtos.CreateUserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("webApi/users")
public class UsersController {

    //Due to the final keyword, this object will be assigned a value by the constructor method.
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        var result = userService.getAllUsers();

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody @Valid CreateUserDto createUserDto) {
        var result = userService.addUser(createUserDto);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") Integer userId) {
        var result = userService.getUserById(userId);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@RequestBody @Valid CreateUserDto userDto, @PathVariable int id) {
        var result = userService.updateUser(userDto, id);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        var result = userService.deleteUser(id);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable(name = "email") String email) {
        var result = userService.getUserByEmail(email);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/addUserToEvent")
    public ResponseEntity<?> addUserToEvent(@RequestParam(name = "userId") int userId, @RequestParam(name = "eventId") int eventId) {
        var result = userService.addUserToEvent(userId, eventId);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/addUserToWorkshop")
    public ResponseEntity<?> addUserToWorkshop(@RequestParam(name = "userId") int userId, @RequestParam(name = "workshopId") int workshopId) {
        var result = userService.addUserToWorkshop(userId, workshopId);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/addModerator")
    public ResponseEntity<?> addModerator(@RequestParam(name = "userId") int userId) {
        var result = userService.addModerator(userId);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/removeModerator")
    public ResponseEntity<?> removeModerator(@RequestParam(name = "userId") int userId) {
        var result = userService.removeModerator(userId);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
