package com.bytebook.userservice.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Optional;

@ControllerAdvice
@Slf4j
public class UserExceptionControllerAdvice {

    private static final Map<Class<? extends RuntimeException>, ErrorCodes> ERROR_MAP = Map.of(
            UserNotFoundException.class, ErrorCodes.NOT_FOUND,
            UserValidationException.class, ErrorCodes.VALIDATION_ERROR,
            UserThirdParyServiceUnavailableException.class, ErrorCodes.THIRD_PARTY_SERVICE_UNAVAILABLE,
            UserUnauthrizedException.class, ErrorCodes.UNAUTHORIZED,
            UserUnknownServerError.class, ErrorCodes.UNKNOWN_SERVER_ERROR
    );

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserErrorResponse> handleAllExceptions(Exception exception) {
        log.error(exception.getMessage(), exception);
        ErrorCodes errorCode = Optional.ofNullable(ERROR_MAP.get(exception.getClass()))
                .orElse(ErrorCodes.UNKNOWN_SERVER_ERROR);
        return ResponseEntity.status(errorCode.getHttpStatus()).body(UserErrorResponse.builder()
                .errorCode(errorCode)
                .message(exception.getMessage())
                .build());
    }

    @Builder
    @Getter
    @Setter
    private final static class UserErrorResponse {
        ErrorCodes errorCode;
        String message;
    }

}
