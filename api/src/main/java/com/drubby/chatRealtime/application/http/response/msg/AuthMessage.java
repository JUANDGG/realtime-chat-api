package com.drubby.chatRealtime.application.http.response.msg;

public enum  AuthMessage {
    LOGIN_SUCCESS("Login successful."),
    USER_REGISTERED("User registered successfully.");

    private final String message;

    private AuthMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
