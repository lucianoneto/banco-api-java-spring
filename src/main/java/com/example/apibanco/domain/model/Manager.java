package com.example.apibanco.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 70)
    private String name;

    @NotBlank
    @Size(max = 11, min = 11)
    @CPF(message = "invalid cpf")
    @Column(unique = true)
    private String cpf;

    @NotBlank
    @Size(max = 20)
    private String phone;

    @Email
    @NotBlank
    @Size(max = 256)
    @Column(unique = true)
    private String email;

    @JsonIgnore
    @JoinColumn(name = "manager_active")
    private Boolean active;

}
