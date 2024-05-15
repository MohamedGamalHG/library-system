package com.example.LibraryManagementSystem.services;

import com.example.LibraryManagementSystem.domains.entities.JpaUser;
import com.example.LibraryManagementSystem.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public String register(JpaUser request)
    {
        JpaUser user = new JpaUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        String token = jwtService.generateKey(user);
//        UserDto t = new UserDto();
//        t.setUsername(token);
        return token;
    }
    public String login(JpaUser request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),request.getPassword()
                )
        );

        JpaUser user = userRepository.findByUsername(request.getUsername());
        String token = jwtService.generateKey(user);
        // UserDto t = new UserDto();
        //t.setUsername(token);
        return token;
    }
}
