package com.example.apibanco.api.model;

import com.example.apibanco.domain.model.ClientAddress;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class ClientInput {

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
    @Size(max = 255)
    @Column(unique = true)
    private String email;

    @NotNull
    private ClientAddress adress;
}
