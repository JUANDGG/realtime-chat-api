package com.drubby.chatRealtime.application.useCase;

import com.drubby.chatRealtime.application.error.AuthError;
import com.drubby.chatRealtime.application.error.AuthErrorMsg;
import com.drubby.chatRealtime.domain.entity.UserEntity;
import com.drubby.chatRealtime.infrastructure.repository.UserRepository;
import com.drubby.chatRealtime.infrastructure.security.CreateUserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set ;
import java.util.Optional;

@Service
public class UserDaoDetailsUseCase implements  UserDetailsService {

    private final UserRepository userRepository;

    public UserDaoDetailsUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByEmail(username);
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("READ")) ;
        authorities.add(new SimpleGrantedAuthority("CREATE")) ;
        authorities.add(new SimpleGrantedAuthority("UPDATE")) ;
        authorities.add(new SimpleGrantedAuthority("DELETE")) ;
        return  CreateUserDetail.createUserDetails(userEntity.get(),authorities);

    }
    public UserDetails customLoadUserByEmail(String userNameEmail , UserEntity userEntity )  {
        if(userNameEmail !=null && userEntity == null){
            Optional<UserEntity> userFindByEmail = userRepository.findByEmail(userNameEmail);
            if (userFindByEmail.isPresent()) {
                return loadUserByUsername(userFindByEmail.get().getEmail());
            } else {
                throw  new AuthError(AuthErrorMsg.USER_NOT_FOUND);
            }
        }
        Optional<UserEntity> userFindByEmail = userRepository.findByEmail(userEntity.getEmail());
        if(userNameEmail==null && userEntity != null){
            if(userFindByEmail.isPresent()) {
                throw new AuthError(AuthErrorMsg.USER_AS_READY_EXISTS);
            }
            else {
                userRepository.save(userEntity);
                return loadUserByUsername(userEntity.getEmail());
            }
        }

        throw new AuthError(AuthErrorMsg.USER_NOT_FOUND);

    }


}
