package com.joao.basicauth.model;

import com.joao.basicauth.core.DatabaseEntity;
import com.joao.basicauth.enums.TipoCargo;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "funcionarios")
public class Funcionario implements Serializable, DatabaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "senha")
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoCargo tipo;

}
