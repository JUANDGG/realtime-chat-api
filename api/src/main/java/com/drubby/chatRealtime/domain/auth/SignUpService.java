package com.drubby.chatRealtime.domain.auth;

import com.drubby.chatRealtime.domain.http.request.AuthBodyRequest;
import com.drubby.chatRealtime.domain.http.response.AuthResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SignUpService {
    private final UserDaoDetailsServiceImpl userDaoDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public SignUpService(UserDaoDetailsServiceImpl userDaoDetailsServiceImpl, PasswordEncoder passwordEncoder) {
        this.userDaoDetailsServiceImpl = userDaoDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }


    //TODO :create this method
    public AuthResponse signUp(AuthBodyRequest authBodyRequest) {
     return  null ;

    }
}
