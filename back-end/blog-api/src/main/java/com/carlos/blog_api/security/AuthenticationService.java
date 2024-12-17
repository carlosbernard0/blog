package com.carlos.blog_api.security;

import com.carlos.blog_api.entity.UserEntity;
import com.carlos.blog_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationService implements UserDetailsService {

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional =  userService.findByUsername(username);

        if(userEntityOptional.isPresent()){
            return userEntityOptional.get();
        }else{
            throw new UsernameNotFoundException("User not found!");
        }
    }
}
