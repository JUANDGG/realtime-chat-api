package com.drubby.chatRealtime.application.error;

public enum AuthErrorMsg {
    INVALID_CREDENTIALS("Invalid credentials."),
    USER_NOT_FOUND("User not found."),
    USER_AS_READY_EXISTS("the user is as ready exits") ,
    ACCOUNT_LOCKED("The account is locked."),
    PASSWORD_EXPIRED("Password has expired."),
    SERVER_ERROR("Internal server error. Please try again.") ,
    EMAIL_INVALID ("Email is invalid."),
    PASSWORD_INVALID ("Password is invalid.") ,
    EMPTY_FIELDS("Fields are empty.") ;


    private final String message;

    private AuthErrorMsg(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

}
