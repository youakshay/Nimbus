package com.service.Nimbus.Service;

import com.service.Nimbus.Respository.CrudRepo;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CrudRepo userRepo;

    public CustomUserDetailsService(CrudRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .map(user -> User.builder()
                        .username(user.username())
                        .password(user.password())
                        .roles(user.userRole())
                                .build())
                                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
