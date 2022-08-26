package com.example.apibanco.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 70)
    private String logradouro;

    @NotBlank
    @Size(max = 40)
    private String setor;

    @NotBlank
    @Size(max = 8)
    private String numero;

    @NotBlank
    @Size(max = 8, min = 8)
    private String CEP;


}
