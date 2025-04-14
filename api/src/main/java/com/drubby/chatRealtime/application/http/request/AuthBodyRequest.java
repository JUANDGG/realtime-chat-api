package com.drubby.chatRealtime.application.http.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@JsonPropertyOrder({"email", "password"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthBodyRequest {

    @NotBlank(message =  "The field cannot be empty")
    @Email(message = "The email format is invalid")
    private String email;

    @NotBlank(message = "The password cannot be empty")
    @Size(min = 8, message = "The password must have at least 8 characters")
    private String password;
}
