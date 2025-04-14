package com.drubby.chatRealtime.application.error;

public  class SignInError extends RuntimeException {
    private SignInErrorMsg signInErrorMsg ;
    public SignInError(SignInErrorMsg signInErrorMsg) {
        super(signInErrorMsg.getMessage());
        this.signInErrorMsg = signInErrorMsg;
    }
}
