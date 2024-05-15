package com.example.LibraryManagementSystem.config;

import com.example.LibraryManagementSystem.domains.entities.JpaUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorImplementation implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JpaUser user;
        if(authentication != null)
        {
            user = (JpaUser) authentication.getPrincipal();
            return Optional.of(user.getUsername());
        }
        else return Optional.of("test User");
    }
}
