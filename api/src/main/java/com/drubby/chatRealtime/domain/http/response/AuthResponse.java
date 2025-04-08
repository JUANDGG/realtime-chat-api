package com.drubby.chatRealtime.domain.http.response;

import org.springframework.http.HttpStatus;

public record AuthResponse(String msg , String token , HttpStatus status) {
}
