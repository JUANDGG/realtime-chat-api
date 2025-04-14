package com.drubby.chatRealtime.application.http.response;

import com.drubby.chatRealtime.application.http.response.msg.AuthMessage;
import org.springframework.http.HttpStatus;

public record AuthResponse(String authMessage , String token , HttpStatus status) {
}
