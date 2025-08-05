package com.bytebook.userservice.exceptions;

public class UserUnauthrizedException extends RuntimeException {
    public UserUnauthrizedException(String message) {
        super(message);
    }

    public UserUnauthrizedException() {
        super("User is unauthenticated");
    }
}
