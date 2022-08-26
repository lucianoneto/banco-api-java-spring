package com.example.apibanco.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private String email;

    @JoinColumn(name = "cliente_ativo")
    private Boolean ativo;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cliente_endereco_id")
    private ClienteEndereco endereco;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gerente_id")
    private Gerente gerente;
}


