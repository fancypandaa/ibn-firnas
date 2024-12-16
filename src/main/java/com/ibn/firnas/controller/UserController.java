package com.ibn.firnas.controller;

import com.ibn.firnas.dto.airCrew.UserDTO;
import com.ibn.firnas.service.UserService;
import com.ibn.firnas.utils.ErrorMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.USER_URI)
public class UserController {
    public static final String USER_URI="/api/aircrew/user";
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<? super UserDTO> createNewUser(@RequestBody UserDTO user){
        try {
            UserDTO savedUser = userService.createNewUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex,HttpStatus.BAD_REQUEST);
        }
    }

}
