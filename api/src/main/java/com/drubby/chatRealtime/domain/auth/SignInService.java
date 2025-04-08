package com.drubby.chatRealtime.domain.auth;


import com.drubby.chatRealtime.domain.http.request.AuthBodyRequest;
import com.drubby.chatRealtime.domain.http.response.AuthResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

    private final UserDaoDetailsServiceImpl userDaoDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public SignInService(UserDaoDetailsServiceImpl userDaoDetailsServiceImpl, PasswordEncoder passwordEncoder) {
        this.userDaoDetailsServiceImpl = userDaoDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse signIn(AuthBodyRequest authBodyRequest) {
        try {
            UserDetails user = userDaoDetailsServiceImpl.loadUserByUsername(authBodyRequest.getEmail());
            if (!passwordEncoder.matches(authBodyRequest.getPassword(), user.getPassword())) {
                /// handled errors
                throw new RuntimeException("Credenciales inválidas");
            }

            //create token
            String token =JwtUtil.createToken((CustomUserDetails) user);

            //handled messages
            return new AuthResponse("msg sussefully login",token , HttpStatus.ACCEPTED);
        }catch (Exception e){
            //handled errors
            System.out.printf("Error: %s\n",e.getMessage());
        }

        return null;

    }

}
