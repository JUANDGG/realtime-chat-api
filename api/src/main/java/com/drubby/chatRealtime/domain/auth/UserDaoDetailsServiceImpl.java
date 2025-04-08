package com.drubby.chatRealtime.domain.auth;

import com.drubby.chatRealtime.domain.entity.UserEntity;
import com.drubby.chatRealtime.persistence.repository.UserRepository;
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
public class UserDaoDetailsServiceImpl implements  UserDetailsService {


    private final UserRepository userRepository;
    public UserDaoDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private  Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> searchFindByEmail = userRepository.findByEmail(username);
        if(searchFindByEmail.isEmpty()){
            throw  new UsernameNotFoundException(username);
        }
        UserEntity userEntity = searchFindByEmail.get();
        return   UserDaoDetailsServiceImpl.customLoadUser(userEntity);
    }





    private  static UserDetails customLoadUser (UserEntity userEntity) {
        /// Permission for default
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("READ")) ;
        authorities.add(new SimpleGrantedAuthority("CREATE")) ;
        authorities.add(new SimpleGrantedAuthority("UPDATE")) ;
        authorities.add(new SimpleGrantedAuthority("DELETE")) ;
        return  CreateUserDetail.createUserDetails(userEntity,authorities);
    }

}
