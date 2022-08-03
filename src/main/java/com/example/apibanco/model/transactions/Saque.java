package com.example.apibanco.model.transactions;

import com.example.apibanco.model.Conta;
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
public class Saque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "saque_valor")
    private Float valor;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "saque_horario")
    private Time horario;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "saque_data")
    private Date data;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

}
