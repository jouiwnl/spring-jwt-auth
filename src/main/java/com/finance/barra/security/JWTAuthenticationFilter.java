package com.finance.barra.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JWTManager jwtManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JWTManager jwtManager) {
        this.authenticationManager = authenticationManager;
        this.jwtManager = jwtManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Credenciais infoLogin = new ObjectMapper().readValue(request.getInputStream(), Credenciais.class);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(infoLogin.getUsername(), infoLogin.getPassword(), new ArrayList<>());
            return authenticationManager.authenticate(auth);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String user = ((User) authResult.getPrincipal()).getUsername();
        String token = jwtManager.generateToken(user);

        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
    }


}
