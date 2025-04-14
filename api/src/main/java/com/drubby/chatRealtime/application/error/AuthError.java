package com.drubby.chatRealtime.application.error;

public  class AuthError extends RuntimeException {
    private AuthErrorMsg signInErrorMsg ;
    public AuthError(AuthErrorMsg signInErrorMsg) {
        super(signInErrorMsg.getMessage());
        this.signInErrorMsg = signInErrorMsg;
    }
}
