package com.drubby.chatRealtime.web.controller.adviceErrorController;

import com.drubby.chatRealtime.application.error.AuthError;
import com.drubby.chatRealtime.application.http.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class AuthExeptionHandlerController {

    @ExceptionHandler(AuthError.class)
    public ResponseEntity<?> handleSignInError(AuthError ex) {
        AuthResponse response = new AuthResponse(
                ex.getMessage(),
                null,
                HttpStatus.UNAUTHORIZED
        );
        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleValidationErrors(WebExchangeBindException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return Mono.just(ResponseEntity.badRequest().body(errors));
    }

}
