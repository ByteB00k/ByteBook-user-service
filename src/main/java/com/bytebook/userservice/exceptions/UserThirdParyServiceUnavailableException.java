package com.bytebook.userservice.exceptions;

public class UserThirdParyServiceUnavailableException extends RuntimeException {
    public UserThirdParyServiceUnavailableException(String message) {
        super(message);
    }

    public UserThirdParyServiceUnavailableException() {
        super("Side service unavailable");
    }
}
