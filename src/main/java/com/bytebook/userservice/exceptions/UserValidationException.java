package com.bytebook.userservice.exceptions;

public class UserValidationException extends RuntimeException {

  public UserValidationException() {
    super("User validation failed");
  }

  public UserValidationException(String message) {
        super(message);
    }
}
