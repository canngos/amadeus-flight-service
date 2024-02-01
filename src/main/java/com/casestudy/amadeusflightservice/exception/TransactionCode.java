package com.casestudy.amadeusflightservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TransactionCode {
    SUCCESS(100, "Success", HttpStatus.OK),
    USER_NOT_FOUND(101, "User not found", HttpStatus.NOT_FOUND),
    TOKEN_EXPIRED(102, "Token expired", HttpStatus.UNAUTHORIZED),
    TOKEN_INVALID(103, "Token invalid", HttpStatus.UNAUTHORIZED),
    INVALID_CREDENTIALS(104, "Invalid credentials", HttpStatus.UNAUTHORIZED),
    EMAIL_EXISTS(105, "Email already exists", HttpStatus.BAD_REQUEST),
    AIRPORT_ALREADY_EXISTS(106, "Airport already exists", HttpStatus.BAD_REQUEST),
    AIRPORT_NOT_FOUND(107, "Airport not found", HttpStatus.NOT_FOUND),
    SAME_AIRPORTS(108, "Departure and arrival airports cannot be the same", HttpStatus.BAD_REQUEST),
    FLIGHT_NOT_FOUND(109, "Flight not found", HttpStatus.NOT_FOUND),
    INVALID_DATE_FORMAT(110, "Invalid date format", HttpStatus.BAD_REQUEST),
    FLIGHT_ALREADY_EXISTS(111, "Flight already exists", HttpStatus.BAD_REQUEST);

    private final int id;
    private final String code;
    private final HttpStatus httpStatus;

    TransactionCode(int id, String code, HttpStatus httpStatus) {
        this.id = id;
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
