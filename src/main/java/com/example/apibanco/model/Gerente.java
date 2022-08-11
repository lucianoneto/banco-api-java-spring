package com.example.apibanco.model;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
@Builder
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
    @CPF(message = "invalid cpf")
    @Column(unique = true)
    private String cpf;

    @NotBlank
    @Size(max = 20)
    private String telefone;

    @Email
    @NotBlank
    @Size(max = 256)
    @Column(unique = true)
    private String email;

}
