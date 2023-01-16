package dev.florinchristian.airportbackend.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleNoUserException(UserNotFoundException e) {
        return new ErrorResponse("There is no such user with the entered email.");
    }

    @ExceptionHandler(WrongCredentialsException.class)
    public ErrorResponse handleWrongCredentials() {
        return new ErrorResponse("The entered password is wrong.");
    }
}
