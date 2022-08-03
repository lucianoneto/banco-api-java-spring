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
public class ClienteEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String setor;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private Long CEP;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


}
