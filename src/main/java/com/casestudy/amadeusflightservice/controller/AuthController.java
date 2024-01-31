package com.casestudy.amadeusflightservice.controller;

import com.casestudy.amadeusflightservice.request.LoginRequest;
import com.casestudy.amadeusflightservice.request.RegisterRequest;
import com.casestudy.amadeusflightservice.response.DefaultMessageResponse;
import com.casestudy.amadeusflightservice.response.LoginResponse;
import com.casestudy.amadeusflightservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<DefaultMessageResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(authService.register(registerRequest), HttpStatus.CREATED);
    }
}
