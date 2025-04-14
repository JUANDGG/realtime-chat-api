package com.drubby.chatRealtime.infrastructure.security.providers;
import com.drubby.chatRealtime.application.useCase.UserDaoDetailsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DaoAuthProvider {
    private final UserDaoDetailsUseCase userDaoDetailsServiceImpl ;

    public DaoAuthProvider(UserDaoDetailsUseCase userDaoDetailsServiceImpl) {
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
