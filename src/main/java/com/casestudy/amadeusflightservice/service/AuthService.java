package com.casestudy.amadeusflightservice.service;


import com.casestudy.amadeusflightservice.request.LoginRequest;
import com.casestudy.amadeusflightservice.request.RegisterRequest;
import com.casestudy.amadeusflightservice.response.DefaultMessageResponse;
import com.casestudy.amadeusflightservice.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);
    DefaultMessageResponse register(RegisterRequest registerRequest);
}
