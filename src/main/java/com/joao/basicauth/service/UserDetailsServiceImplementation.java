package com.joao.basicauth.service;

import com.joao.basicauth.core.BasicRepository;
import com.joao.basicauth.model.Funcionario;
import com.joao.basicauth.model.QFuncionario;
import com.joao.basicauth.security.User;
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
