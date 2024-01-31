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
    EMAIL_EXISTS(105, "Email already exists", HttpStatus.CONFLICT);

    private final int id;
    private final String code;
    private final HttpStatus httpStatus;

    TransactionCode(int id, String code, HttpStatus httpStatus) {
        this.id = id;
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
