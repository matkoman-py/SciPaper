package com.example.UserService.controller;

import com.example.UserService.domain.LoginDTO;
import com.example.UserService.domain.User;
import com.example.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-service")
public class UserController {

    @Autowired
    private UserService userService;

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity handleException(IllegalArgumentException illegalArgumentException) {
        return new ResponseEntity(illegalArgumentException.getMessage(), HttpStatus.CONFLICT);
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping(value="/listusers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @GetMapping(value="/userexists/{username}")
    public ResponseEntity<Boolean> userExists(@PathVariable String username) {
        return ResponseEntity.ok(userService.userExists(username));
    }

    @GetMapping(value="/isloggedin/{username}")
    public ResponseEntity<Boolean> isLoggedIn(@PathVariable String username) {
        return ResponseEntity.ok(userService.isLoggedIn(username));
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDto) {
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @GetMapping(value="/getname/{username}")
    public ResponseEntity<String> getName(@PathVariable String username) {
        return ResponseEntity.ok(userService.getName(username));
    }

}
