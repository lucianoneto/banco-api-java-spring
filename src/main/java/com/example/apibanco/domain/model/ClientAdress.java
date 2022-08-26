package com.example.apibanco.domain.model;

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
public class ClientAdress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 70)
    private String street;

    @NotBlank
    @Size(max = 40)
    private String district;

    @NotBlank
    @Size(max = 8)
    private String number;

    @NotBlank
    @Size(max = 8, min = 8)
    private String CEP;


}
