package com.example.apibanco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 70)
    private String nome;

    @NotBlank
    @Size(max = 11, min = 11)
    @CPF(message="cpf registered in the database")
    private String CPF;

    @NotBlank
    @Size(max = 20)
    private String telefone;

    @Email
    @NotBlank
    @Size(max = 256)
    private String email;

    @NotBlank
    @Size(max = 20)
    private String tipoConta;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gerente_id")
    private Gerente gerente;

}


