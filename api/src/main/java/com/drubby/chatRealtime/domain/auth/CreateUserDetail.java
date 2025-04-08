package com.drubby.chatRealtime.domain.auth;

import com.drubby.chatRealtime.domain.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import java.util.List ;
import java.util.Set;


public abstract class CreateUserDetail {

    public static CustomUserDetails createUserDetails ( UserEntity userEntity , Set<GrantedAuthority> authorityList   ){
        return new CustomUserDetails(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getUsername(),
                userEntity.getPasswordHash(),
                userEntity.isEnabled(),
                userEntity.isAccountNonExpired(),
                userEntity.isCredentialsNonExpired(),
                userEntity.isAccountNonLocked(),
                authorityList
        );
    }
}
