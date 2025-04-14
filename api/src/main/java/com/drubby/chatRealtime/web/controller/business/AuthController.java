package com.drubby.chatRealtime.web.controller.business;

import com.drubby.chatRealtime.application.http.request.AuthBodyRequest;
import com.drubby.chatRealtime.domain.service.SignInService;
import com.drubby.chatRealtime.domain.service.SignUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final SignInService signInService;
    private final SignUpService signUpService;

    public AuthController(SignInService signInService, SignUpService signUpService) {
        this.signInService = signInService;
        this.signUpService = signUpService;
    }

    @GetMapping
    public ResponseEntity<?> signIn (@RequestBody AuthBodyRequest authBodyRequest) {
        return ResponseEntity.ok(signInService.signIn(authBodyRequest));
    }

    @GetMapping
    public ResponseEntity<?> signUp (@RequestBody AuthBodyRequest authBodyRequest) {
        return ResponseEntity.ok(signUpService.signUp(authBodyRequest));
    }
}
