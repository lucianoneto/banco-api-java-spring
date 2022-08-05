package com.example.apibanco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Float saldo;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date dataCriacao;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Time horaCriacao;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

}
