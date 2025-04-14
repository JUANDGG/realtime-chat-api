package com.drubby.chatRealtime.application.useCase;

import com.drubby.chatRealtime.application.http.response.msg.AuthMessage;
import com.drubby.chatRealtime.domain.service.SignUpService;
import com.drubby.chatRealtime.infrastructure.security.CustomUserDetail;
import com.drubby.chatRealtime.infrastructure.security.jwt.JwtUtil;
import com.drubby.chatRealtime.domain.entity.UserEntity;
import com.drubby.chatRealtime.application.http.request.AuthBodyRequest;
import com.drubby.chatRealtime.application.http.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SignUpUseCase implements SignUpService {
    private final UserDaoDetailsUseCase userDaoDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public SignUpUseCase(UserDaoDetailsUseCase userDaoDetailsServiceImpl, PasswordEncoder passwordEncoder) {
        this.userDaoDetailsServiceImpl = userDaoDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse signUp(AuthBodyRequest authBodyRequest) {
        UserDetails user = userDaoDetailsServiceImpl.customLoadUserByEmail(null , UserEntity.builder().email(authBodyRequest.getEmail())
                .email(authBodyRequest.getEmail())
                .passwordHash(passwordEncoder.encode(authBodyRequest.getPassword()))
                .createdAt(LocalDateTime.now())
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .enabled(true)
                .build());
        String token = JwtUtil.createToken((CustomUserDetail) user);
        return new AuthResponse(AuthMessage.USER_REGISTERED.getMessage() ,token , HttpStatus.ACCEPTED);
    }
}
