package com.flights.book.controller;

import com.flights.book.dto.model.AuthDTO;
import com.flights.book.model.AuthUser;
import com.flights.book.model.User;
import com.flights.book.repository.UserRepository;
import com.flights.book.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AuthDTO userLogin) throws IllegalAccessException{
        User user = new User();
        user.setUserName(userLogin.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userLogin.getPassword()));
        user.setEmail(userLogin.getEmail());
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO userLogin) throws IllegalAccessException{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getUsername(),userLogin.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUser userInfo = (AuthUser) authentication.getPrincipal();
        log.info("Token requested for user :{}", authentication.getAuthorities());
        String token = authService.generateToken(authentication);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
