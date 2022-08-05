package com.example.apibanco.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Gerente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 70)
    private String nome;

    @NotBlank
    @Size(max = 11, min = 11)
    @CPF(message="cpf registered in the database")
    @Column(unique = true)
    private String CPF;

    @NotBlank
    @Size(max = 20)
    private String telefone;

    @Email
    @NotBlank
    @Size(max = 256)
    private String email;


}
