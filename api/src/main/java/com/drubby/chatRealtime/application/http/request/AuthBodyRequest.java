package com.drubby.chatRealtime.application.http.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
public class AuthBodyRequest {
    private String email;
    private String password;
}
