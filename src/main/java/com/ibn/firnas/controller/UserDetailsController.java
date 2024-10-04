package com.ibn.firnas.controller;

import com.ibn.firnas.dto.airCrew.UserDetailsDTO;
import com.ibn.firnas.exception.CustomNotFoundException;
import com.ibn.firnas.service.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserDetailsController.USER_DETAILS_URI)
public class UserDetailsController {
    private final static Logger logger = LoggerFactory.getLogger(UserDetailsController.class);

    public static final String USER_DETAILS_URI="/api/aircrew/userDetails";
    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable Long userId){
            UserDetailsDTO userDetailsDTO=userDetailsService.findUserDetailsById(userId);
            return new ResponseEntity<>(userDetailsDTO,HttpStatus.OK);
    }
    @PostMapping("/{userId}")
    public ResponseEntity<?> createNewUserDetails(@PathVariable Long userId, @RequestBody UserDetailsDTO userDetails){
            UserDetailsDTO savedUserDetails = userDetailsService.createNewUserDetails(userId,userDetails);
            return new ResponseEntity<>(savedUserDetails, HttpStatus.CREATED);

    }
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserDetails(@PathVariable Long userId, @RequestBody UserDetailsDTO userDetails){
            UserDetailsDTO updatedUserDetails = userDetailsService.updateUserDetails(userId,userDetails);
            return new ResponseEntity<>(updatedUserDetails, HttpStatus.OK);
    }
}
