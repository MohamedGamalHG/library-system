package com.example.LibraryManagementSystem.filters;


import com.example.LibraryManagementSystem.services.JwtService;
import com.example.LibraryManagementSystem.services.UserDetailsServiceImp;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImp userDetailsServiceImp;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsServiceImp userDetailsServiceImp) {
        this.jwtService = jwtService;
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException
    {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response);
            return;
        }
        String token = authHeader.substring(7);
        String username = jwtService.extractUserName(token);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetails user = userDetailsServiceImp.loadUserByUsername(username);
            if(jwtService.isValid(token,user))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user,null,user.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }

        filterChain.doFilter(request,response);
    }
}
