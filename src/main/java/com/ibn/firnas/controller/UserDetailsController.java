package com.ibn.firnas.controller;

import com.ibn.firnas.dto.airCrew.UserDetailsDTO;
import com.ibn.firnas.exception.CustomException;
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
    public ResponseEntity<? super UserDetailsDTO> getUserDetails(@PathVariable Long userId){
        try {
            UserDetailsDTO userDetailsDTO=userDetailsService.findUserDetailsById(userId);
            return new ResponseEntity<>(userDetailsDTO,HttpStatus.OK);
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/{userId}")
    public ResponseEntity<? super UserDetailsDTO> createNewUserDetails(@PathVariable Long userId, @RequestBody UserDetailsDTO userDetails){
        try {
            UserDetailsDTO savedUserDetails = userDetailsService.createNewUserDetails(userId,userDetails);
            return new ResponseEntity<>(savedUserDetails, HttpStatus.CREATED);
        }
        catch (CustomException exception){
            logger.error(exception.getStackTrace().toString());
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/{userId}")
    public ResponseEntity<? super UserDetailsDTO> updateUserDetails(@PathVariable Long userId, @RequestBody UserDetailsDTO userDetails){
        try {
            UserDetailsDTO updatedUserDetails = userDetailsService.updateUserDetails(userId,userDetails);
            return new ResponseEntity<>(updatedUserDetails, HttpStatus.OK);
        }
        catch (CustomException exception){
            logger.error(exception.getStackTrace().toString());
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
