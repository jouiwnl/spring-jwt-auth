package com.finance.barra.security;

import com.finance.barra.enums.TipoCargo;
import com.finance.barra.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public User(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.username = funcionario.getUsuario();
        this.password = funcionario.getSenha();
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(funcionario.getTipo().getDesc()));
    }

    public boolean hasRole(TipoCargo tipoCargo) {
        return this.getAuthorities().contains(new SimpleGrantedAuthority(tipoCargo.getDesc()));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
