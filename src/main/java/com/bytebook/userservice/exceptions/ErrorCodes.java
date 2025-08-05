package com.bytebook.userservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodes {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    THIRD_PARTY_SERVICE_UNAVAILABLE(HttpStatus.FAILED_DEPENDENCY),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
    UNKNOWN_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);


    private final HttpStatus httpStatus;

    ErrorCodes(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
