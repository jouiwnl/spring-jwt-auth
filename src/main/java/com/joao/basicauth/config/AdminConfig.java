package com.joao.basicauth.config;

import com.joao.basicauth.core.BasicRepository;
import com.joao.basicauth.enums.TipoCargo;
import com.joao.basicauth.model.Funcionario;
import com.joao.basicauth.model.QFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class AdminConfig {

    @Autowired
    private BasicRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void verifyRootUser() {
        Funcionario funcionario = repository.findOne(Funcionario.class, QFuncionario.funcionario.usuario.eq("admin2"));

        if (funcionario != null) {
            return;
        }

        Funcionario admin = Funcionario.builder()
                .nomeCompleto("Usuário administrador 2")
                .usuario("admin2")
                .senha(encoder.encode("admin"))
                .tipo(TipoCargo.ADM)
                .build();

        repository.save(admin);
    }

}
