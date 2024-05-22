package com.flights.book.service;

import com.flights.book.model.AuthUser;
import com.flights.book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        AuthUser authUser = userRepository.
                findByUserName(username)
                .map(AuthUser::new)
                .orElseThrow(()-> new UsernameNotFoundException("User name not found: " + username));
        return authUser;
    }
}
