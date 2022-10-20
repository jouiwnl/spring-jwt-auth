package com.joao.basicauth.controller;

import com.joao.basicauth.core.BasicRepository;
import com.joao.basicauth.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/funcionarios")
@CrossOrigin("*")
@RestController
public class FuncionarioController {

    @Autowired
    private BasicRepository repository;

    @PostMapping
    public Funcionario create(@RequestBody Funcionario funcionario) {
        return this.repository.save(funcionario);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public List<Funcionario> findAll() {
        return this.repository.findAll(Funcionario.class);
    }

}
