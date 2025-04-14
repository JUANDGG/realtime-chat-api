package com.drubby.chatRealtime.application.error;

public enum SignInErrorMsg {
    INVALID_CREDENTIALS("Invalid credentials."),
    USER_NOT_FOUND("User not found."),
    ACCOUNT_LOCKED("The account is locked."),
    PASSWORD_EXPIRED("Password has expired."),
    SERVER_ERROR("Internal server error. Please try again.");


    private final String message;

    private SignInErrorMsg(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

}
