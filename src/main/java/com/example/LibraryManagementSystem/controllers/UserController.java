package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.domains.entities.JpaUser;
import com.example.LibraryManagementSystem.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Tag(name = "User")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody JpaUser request)
    {
        return ResponseEntity.ok(userService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JpaUser request)
    {
        String s = userService.login(request);
        return ResponseEntity.ok(s);

    }
}
