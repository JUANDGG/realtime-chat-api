package com.drubby.chatRealtime.infrastructure.security;

import com.drubby.chatRealtime.domain.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;


public abstract class CreateUserDetail {
    public static CustomUserDetail createUserDetails (UserEntity userEntity , Set<GrantedAuthority> authorityList   ){
        return new CustomUserDetail(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getPasswordHash(),
                userEntity.isEnabled(),
                userEntity.isAccountNonExpired(),
                userEntity.isCredentialsNonExpired(),
                userEntity.isAccountNonLocked(),
                authorityList
        );
    }
}
