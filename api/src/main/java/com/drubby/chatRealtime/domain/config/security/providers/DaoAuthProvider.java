package com.drubby.chatRealtime.domain.config.security.providers;
import com.drubby.chatRealtime.domain.auth.UserDaoDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DaoAuthProvider {
    private final UserDaoDetailsServiceImpl userDaoDetailsServiceImpl ;

    public DaoAuthProvider(UserDaoDetailsServiceImpl userDaoDetailsServiceImpl) {
        this.userDaoDetailsServiceImpl =userDaoDetailsServiceImpl ;
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider (){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDaoDetailsServiceImpl);
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
