package com.finance.barra.service;

import com.finance.barra.core.BasicRepository;
import com.finance.barra.model.Funcionario;
import com.finance.barra.model.QFuncionario;
import com.finance.barra.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    private BasicRepository repository;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        return new User(repository.findOne(Funcionario.class, QFuncionario.funcionario.usuario.eq(user)));
    }
}
