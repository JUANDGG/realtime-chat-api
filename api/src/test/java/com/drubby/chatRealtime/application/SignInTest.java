package com.drubby.chatRealtime.application;

import com.drubby.chatRealtime.application.http.request.AuthBodyRequest;
import com.drubby.chatRealtime.application.http.response.AuthResponse;
import com.drubby.chatRealtime.application.http.response.msg.AuthMessage;
import com.drubby.chatRealtime.application.useCase.SignInUseCase;
import com.drubby.chatRealtime.application.useCase.UserDaoDetailsUseCase;
import com.drubby.chatRealtime.domain.entity.UserEntity;
import com.drubby.chatRealtime.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SignInTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDaoDetailsUseCase userDaoDetailsUseCase;


    @InjectMocks
    private SignInUseCase signInService;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void signInValidCredentials() {

        AuthBodyRequest authBodyRequest = new AuthBodyRequest();
        authBodyRequest.setEmail("juanguiza65@gmail.com");
        authBodyRequest.setPassword("juan1234");


        UserEntity userEntity = UserEntity.builder()
                .email("juanguiza65@gmail.com")
                .passwordHash(passwordEncoder.encode("juan123"))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .createdAt(LocalDateTime.now())
                .credentialsNonExpired(true)
                .enabled(true)
                .build();


        when(userDaoDetailsUseCase.customLoadUserByEmail("juanguiza65@gmail.com", null))
                .thenReturn((UserDetails) userEntity);


        AuthResponse response = signInService.signIn(authBodyRequest);


        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.status());
        assertEquals(AuthMessage.LOGIN_SUCCESS.getMessage(), response.authMessage());
        assertNotNull(response.token());
    }
}
