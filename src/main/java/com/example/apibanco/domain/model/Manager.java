package com.example.apibanco.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 70)
    private String name;

    @Size(max = 11, min = 11, message = "cpf must be formed by 11 digits without punctuation")
    @NotBlank
    @CPF
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
