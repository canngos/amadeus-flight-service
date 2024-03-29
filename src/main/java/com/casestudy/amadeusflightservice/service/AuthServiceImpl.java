package com.casestudy.amadeusflightservice.service;

import com.casestudy.amadeusflightservice.entity.Customer;
import com.casestudy.amadeusflightservice.exception.BusinessException;
import com.casestudy.amadeusflightservice.exception.Status;
import com.casestudy.amadeusflightservice.exception.TransactionCode;
import com.casestudy.amadeusflightservice.repository.CustomerRepository;
import com.casestudy.amadeusflightservice.request.LoginRequest;
import com.casestudy.amadeusflightservice.request.RegisterRequest;
import com.casestudy.amadeusflightservice.response.DefaultMessageResponse;
import com.casestudy.amadeusflightservice.response.LoginResponse;
import com.casestudy.amadeusflightservice.response.base.BaseBody;
import com.casestudy.amadeusflightservice.response.body.DefaultMessageBody;
import com.casestudy.amadeusflightservice.response.body.LoginResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Customer customer = checkCustomer(email);

        authenticateCustomer(email, password);
        String token = jwtService.generateToken(customer);
        Date expirationDate = jwtService.extractExpiration(token);

        LoginResponse loginResponse = new LoginResponse();
        LoginResponseBody body = new LoginResponseBody();
        body.setToken(token);
        body.setExpirationDate(expirationDate);
        loginResponse.setBody(new BaseBody<>(body));
        loginResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return loginResponse;
    }

    @Override
    public DefaultMessageResponse register(RegisterRequest registerRequest) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(registerRequest.getEmail());
        if (customerOptional.isPresent()) {
            throw new BusinessException(TransactionCode.EMAIL_EXISTS);
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(registerRequest, customer);
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        customer.setPassword(encodedPassword);
        customerRepository.save(customer);

        DefaultMessageResponse response = new DefaultMessageResponse();
        DefaultMessageBody body = new DefaultMessageBody("Customer registered successfully");
        response.setBody(new BaseBody<>(body));
        response.setStatus(new Status(TransactionCode.SUCCESS));
        return response;
    }

    private Customer checkCustomer(String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);

        if (customerOptional.isEmpty()) {
            throw new BusinessException(TransactionCode.USER_NOT_FOUND);
        }
        return customerOptional.get();
    }

    private void authenticateCustomer(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            throw new BusinessException(TransactionCode.INVALID_CREDENTIALS);
        }
    }
}
