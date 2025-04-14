package com.drubby.chatRealtime.domain.service;

import com.drubby.chatRealtime.application.http.request.AuthBodyRequest;
import com.drubby.chatRealtime.application.http.response.AuthResponse;

public interface SignInService {
    AuthResponse signIn(AuthBodyRequest authBodyRequest) ;
}
