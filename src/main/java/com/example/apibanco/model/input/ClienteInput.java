package com.example.apibanco.model.input;

import com.example.apibanco.model.ClienteEndereco;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class ClienteInput {

    @Valid
    @NotNull
    private ClienteEndereco endereco;

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

    @NotBlank
    @Size(max = 20)
    private String tipoConta;

}
