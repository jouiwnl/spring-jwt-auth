package com.finance.barra.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTManager jwtManager;
    private UserDetailsService userDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JWTManager jwtManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtManager = jwtManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken auth = this.getAuth(authHeader.substring(7));

            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
    }

    protected UsernamePasswordAuthenticationToken getAuth(String token) {
        if (jwtManager.tokenValid(token)) {
            String username = jwtManager.getUser(token);
            UserDetails user = userDetailsService.loadUserByUsername(username);

            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        }

        return null;
    }
}
