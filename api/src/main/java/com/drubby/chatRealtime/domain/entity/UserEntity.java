package com.drubby.chatRealtime.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(unique = true , nullable = false , length = 150)
    private String email;

    @Column(name = "password_hash", nullable = false , length = 255)
    private String passwordHash;

    @Column(name = "created_at", nullable = false ,columnDefinition = "TIMESTAMP DEFAULT now()")
    private LocalDateTime createdAt;

    @Column(name ="account_non_expired",nullable = false)
    private boolean  accountNonExpired ;

    @Column(name ="account_non_locked",nullable = false)
    private boolean accountNonLocked ;

    @Column(name ="credentials_non_expired",nullable = false)
    private boolean credentialsNonExpired ;


    @Column(nullable = false  ,columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean enabled ;
}

