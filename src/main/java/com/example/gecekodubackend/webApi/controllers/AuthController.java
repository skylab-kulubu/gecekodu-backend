package com.example.gecekodubackend.webApi.controllers;

import com.example.gecekodubackend.business.abstracts.UserService;
import com.example.gecekodubackend.core.security.JwtService;
import com.example.gecekodubackend.core.utilities.results.DataResult;
import com.example.gecekodubackend.core.utilities.results.ErrorDataResult;
import com.example.gecekodubackend.core.utilities.results.SuccessDataResult;
import com.example.gecekodubackend.entity.dtos.auth.AuthDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webApi/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private UserService userService;

    public AuthController(UserService userService,AuthenticationManager authenticationManager, JwtService jwtService){
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }


    @PostMapping("/generateToken")
    public DataResult<String> generateToken(@RequestBody AuthDto authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return new SuccessDataResult<String>(jwtService.generateToken(authRequest.getEmail()), "Token generated successfully");
        }
        return new ErrorDataResult<>("Invalid email or password");
    }
}
