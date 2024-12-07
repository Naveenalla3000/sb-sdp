package com.naveen.be.filter;

import com.naveen.be.srv.JwtService;
import com.naveen.be.srv.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MyUserDetailsService myUserDetailsService; // Direct injection of MyUserDetailsService

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Retrieve the Authorization header
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        String userName = null;

        // If the Authorization header is present and starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Extract token from "Bearer <token>"
            userName = jwtService.extractUserName(token); // Extract the username from the token
        }

        // Proceed if a token is found and the SecurityContext is empty
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);  // Load the user details
            if (jwtService.validateToken(token, userDetails)) {  // Validate the token
                // Create the authentication token with the user's authorities
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // Set additional details
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication object to the security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
