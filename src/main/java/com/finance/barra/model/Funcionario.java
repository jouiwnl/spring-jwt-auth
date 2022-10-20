package com.finance.barra.model;

import com.finance.barra.core.DatabaseEntity;
import com.finance.barra.enums.TipoCargo;
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
