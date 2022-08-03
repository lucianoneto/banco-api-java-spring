package com.example.apibanco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private Long CPF;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String tipoConta;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gerente_id")
    private Gerente gerente;

}


