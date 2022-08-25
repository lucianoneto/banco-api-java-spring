package com.example.apibanco.domain.model.transactions;

import com.example.apibanco.domain.model.Conta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "deposito_valor")
    @Min(1)
    private Float valor;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "deposito_horario")
    private Time horario;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "deposito_data")
    private Date data;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

}
