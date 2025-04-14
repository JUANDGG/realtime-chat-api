package com.drubby.chatRealtime.web.controller.adviceErrorController;

import com.drubby.chatRealtime.application.error.SignInError;
import com.drubby.chatRealtime.application.http.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExeptionHandlerController {

    @ExceptionHandler(SignInError.class)
    public ResponseEntity<?> handleSignInError(SignInError ex) {
        AuthResponse response = new AuthResponse(
                ex.getMessage(),
                null,
                HttpStatus.UNAUTHORIZED
        );
        return new ResponseEntity<>(response, response.status());
    }

}
