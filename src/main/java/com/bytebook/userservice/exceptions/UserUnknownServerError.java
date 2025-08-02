package com.bytebook.userservice.exceptions;

public class UserUnknownServerError extends RuntimeException {
    public UserUnknownServerError(String message) {
        super(message);
    }
    public UserUnknownServerError() {
        super("User service unknown error");
    }
}
