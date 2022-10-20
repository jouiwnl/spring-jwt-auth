package com.finance.barra.service;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserSS {
    public static String authenticated() {
        try {
            return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}