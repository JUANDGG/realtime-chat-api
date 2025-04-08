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
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "user_name", unique = true , nullable = false , length = 30)
    private String username;

    @Column(unique = true , nullable = false , length = 150)
    private String email;

    @Column(nullable = false , length = 255)
    private String passwordHash;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    private LocalDateTime createdAt;

    @Column(name ="account_non_expired" )
    private boolean  accountNonExpired ;

    @Column(name ="account_non_locked" )
    private boolean accountNonLocked ;

    @Column(name ="credentials_non_expired" )
    private boolean credentialsNonExpired ;

    private boolean enabled ;
}

