package com.drubby.chatRealtime.application.useCase;


import com.drubby.chatRealtime.application.error.SignInError;
import com.drubby.chatRealtime.application.error.SignInErrorMsg;
import com.drubby.chatRealtime.application.http.response.msg.AuthMessage;
import com.drubby.chatRealtime.domain.service.SignInService;
import com.drubby.chatRealtime.infrastructure.security.CustomUserDetail;
import com.drubby.chatRealtime.infrastructure.security.jwt.JwtUtil;
import com.drubby.chatRealtime.application.http.request.AuthBodyRequest;
import com.drubby.chatRealtime.application.http.response.AuthResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignInUseCase implements SignInService {

    private final UserDaoDetailsUseCase userDaoDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public SignInUseCase(UserDaoDetailsUseCase userDaoDetailsServiceImpl, PasswordEncoder passwordEncoder) {
        this.userDaoDetailsServiceImpl = userDaoDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse signIn(AuthBodyRequest authBodyRequest) {
        UserDetails user = userDaoDetailsServiceImpl.customLoadUserByEmail(authBodyRequest.getEmail(), null);

        if (!passwordEncoder.matches(authBodyRequest.getPassword(), user.getPassword())) {
            throw new SignInError(SignInErrorMsg.INVALID_CREDENTIALS);
        }

        String token = JwtUtil.createToken((CustomUserDetail) user);
        return new AuthResponse(AuthMessage.LOGIN_SUCCESS.getMessage(), token, HttpStatus.ACCEPTED);
    }

}
