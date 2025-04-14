package com.drubby.chatRealtime.web.controller.business;

import com.drubby.chatRealtime.application.http.request.AuthBodyRequest;
import com.drubby.chatRealtime.domain.service.SignInService;
import com.drubby.chatRealtime.domain.service.SignUpService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final SignInService signInService;
    private final SignUpService signUpService;

    public AuthController(SignInService signInService, SignUpService signUpService) {
        this.signInService = signInService;
        this.signUpService = signUpService;
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn (@RequestBody @Validated AuthBodyRequest authBodyRequest) {
        return ResponseEntity.ok(signInService.signIn(authBodyRequest));
    }

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp (@RequestBody  @Validated AuthBodyRequest authBodyRequest) {
        return ResponseEntity.ok(signUpService.signUp(authBodyRequest));
    }
}
