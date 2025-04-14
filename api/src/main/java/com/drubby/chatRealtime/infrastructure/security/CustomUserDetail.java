package com.drubby.chatRealtime.infrastructure.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class CustomUserDetail extends User {
    private Long userId;


    public CustomUserDetail(Long userId , String email, String password,
                            boolean enabled, boolean accountNonExpired,
                            boolean credentialsNonExpired, boolean accountNonLocked,
                            Collection<? extends GrantedAuthority> authorities) {
        super(email, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }


    public Long getUserId() {
        return this.userId;
    }


    public String getEmail() {
        return super.getUsername();
    }


    @Override
    public boolean isAccountNonExpired() {
        return super.isAccountNonExpired();
    }


    @Override
    public boolean isAccountNonLocked() {
        return super.isAccountNonLocked();
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired();
    }


    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }


    public boolean isAccountExpired() {
        return !isAccountNonExpired();
    }


    public boolean isAccountLocked() {
        return !isAccountNonLocked();
    }


    public boolean isCredentialsExpired() {
        return !isCredentialsNonExpired();
    }


    public boolean isDisabled() {
        return !isEnabled();
    }
}